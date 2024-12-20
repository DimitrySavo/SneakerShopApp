package com.example.sneakershopapp.model

import android.util.Log
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class DataService {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    suspend fun getShoes(): FunctionResult<List<Shoe>> {
        try {
            val requestTimeStart = System.currentTimeMillis()
            val shoes = mutableListOf<Shoe>()
            val snapshot = db.collection("Shoes").get().await()
            val requestTimeEnd = System.currentTimeMillis()
            Log.i("Timer", "Request time: ${requestTimeEnd - requestTimeStart}")

            Log.i("DataService", "getSnapshot: ${snapshot.documents}")

            val startTime = System.currentTimeMillis()
            for (document in snapshot.documents) {
                val shoe = document.toObject<Shoe>()
                shoe?.let {
                    shoe.getPriceAndStock()
                    shoes.add(it)
                }
            }

            val endTime = System.currentTimeMillis()
            Log.i("Timer", "Time result: ${endTime - startTime}")
            Log.i("DataService", "getShoes: $shoes")

            return FunctionResult.Success(shoes)
        } catch (e: Exception) {
            return FunctionResult.Error(e.toString())
        }
    }

    suspend fun markShoeAsFavorite(shoeId: String): FunctionResult<Unit> {
        val currentUserUid = auth.currentUser?.uid ?: return FunctionResult.Error("User is null")
        val userRef = db.collection("users").document(currentUserUid)
        userRef.update("favorites", FieldValue.arrayUnion(shoeId)).await()
        return FunctionResult.Success(Unit)
    }

    suspend fun unmarkShoeAsFavorite(shoeId: String): FunctionResult<Unit> {
        val currentUserUid = auth.currentUser?.uid ?: return FunctionResult.Error("User is null")
        val userRef = db.collection("users")
            .document(currentUserUid)
        userRef.update("favorites", FieldValue.arrayRemove(shoeId)).await()
        return FunctionResult.Success(Unit)
    }

    suspend fun getUserDoc(): FunctionResult<User> {
        val currentUserUid = auth.currentUser?.uid ?: return FunctionResult.Error("User is null")
        val userSnapshot = db.collection("users").document(currentUserUid).get().await()
        val user =
            userSnapshot.toObject<User>() ?: return FunctionResult.Error("User document is null")
        return FunctionResult.Success(user)
    }

    private suspend fun updateUserDoc(newUser: User): FunctionResult<Unit> {
        val currentUserUid = auth.currentUser?.uid ?: return FunctionResult.Error("User is null")
        val userRef = db.collection("users").document(currentUserUid).update(newUser.toMap()).await()
        return FunctionResult.Success(Unit)
    }

    suspend fun getFavorites(): FunctionResult<List<String>> {
        return when (val user = getUserDoc()) {
            is FunctionResult.Success -> {
                FunctionResult.Success(user.data.favorites)
            }

            is FunctionResult.Error -> {
                FunctionResult.Error("Something went wrong in getting user from firebase")
            }
        }
    }

    suspend fun getUserName(): FunctionResult<String> {
        return when (val user = getUserDoc()) {
            is FunctionResult.Success -> FunctionResult.Success(user.data.name)
            is FunctionResult.Error -> FunctionResult.Error("Cant get name from app data")
        }
    }

    suspend fun getUnauthorizedName(userEmail: String): FunctionResult<String> {
        val userSnapshot = db.collection("users")
            .whereEqualTo("email", userEmail)
            .get()
            .await()
        return if (!userSnapshot.isEmpty) {
            FunctionResult.Success(userSnapshot.documents[0].get("name").toString())
        } else {
            FunctionResult.Error("Failed to get user with such email")
        }
    }

    suspend fun addToCart(shoeId: String, shoe: CartShoe): FunctionResult<Unit> {
        val currentUserUid = auth.currentUser?.uid ?: return FunctionResult.Error("User is null")
        val shoeRef = db.collection("Shoes").document(shoeId).get().await()
        if (shoeRef.exists().not()) {
            return FunctionResult.Error("Shoe not found")
        } else if (shoeRef.toObject<Shoe>()?.sizes?.get(shoe.size)?.inStock == 0L) {
            return FunctionResult.Error("Shoe is out of stock") // should replace with some better callbacks but for now it's ok
        }
        shoe.shoeRef = shoeRef.reference
        db.collection("users").document(currentUserUid).collection("cart").add(shoe)
            .await() // cant use cloud functions because of free plan
        return FunctionResult.Success(Unit)
    }

    suspend fun registerUser(user: User, password: String): FunctionResult<String> {
        try {
            val authResult = auth.createUserWithEmailAndPassword(user.email, password).await()
            val currentUserUid = authResult.user?.uid ?: return FunctionResult.Error("User is null")

            db.collection("users").document(currentUserUid).set(user).await()

            Log.i("DataService", "registerUser: $currentUserUid")
            return FunctionResult.Success(currentUserUid)
        } catch (e: FirebaseAuthUserCollisionException) {
            Log.e("DataService", "Email is already in use")
            return FunctionResult.Error("Email is already in use")
        } catch (e: Exception) {
            Log.e("DataService", "registerUser: ${e.message}")
            return FunctionResult.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun loginUser(email: String, password: String): FunctionResult<User> {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Log.i("DataService", "loginUser: $email")
            return when (val user = getUserDoc()) {
                is FunctionResult.Success -> FunctionResult.Success(user.data)
                is FunctionResult.Error -> FunctionResult.Error(user.message)
            }
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is FirebaseAuthException -> when (e.errorCode) {
                    "ERROR_INVALID_EMAIL" -> "Неверный формат email."
                    "ERROR_USER_NOT_FOUND" -> "Пользователь не найден."
                    "ERROR_WRONG_PASSWORD" -> "Неверный пароль."
                    "ERROR_USER_DISABLED" -> "Аккаунт пользователя отключен."
                    "ERROR_TOO_MANY_REQUESTS" -> "Слишком много попыток входа, попробуйте позже."
                    else -> "Ошибка аутентификации: ${e.message}"
                }

                else -> "Неизвестная ошибка: ${e.message}"
            }
            Log.e("DataService", "loginUser: $errorMessage")
            return FunctionResult.Error(errorMessage)
        }
    }

    fun logoutUser() {
        auth.signOut()
    }

    fun getUserUid(): FunctionResult<String?> {
        return try {
            if (auth.currentUser == null) {
                FunctionResult.Success(null)
            } else {
                FunctionResult.Success(auth.currentUser!!.uid)
            }
        } catch (e: Exception) {
            FunctionResult.Error(e.toString())
        }
    }

    suspend fun sendResetPasswordEmail(userEmail: String): FunctionResult<String> {
        if (db.collection("users").whereEqualTo("email", userEmail).get().await().isEmpty) {
            Log.e("sendResetPasswordEmail", "Invalid email: $userEmail not found")
            return FunctionResult.Error("Пользователь с таким email не найден")
        }

        return try {
            auth.sendPasswordResetEmail(userEmail).await()
            Log.i("sendResetPasswordEmail", "Password reset email successfully sent to $userEmail")
            FunctionResult.Success("Письмо с ссылкой успешно отправлено на почту $userEmail")
        } catch (ex: FirebaseAuthInvalidUserException) {
            Log.e("sendResetPasswordEmail", "Invalid email: $userEmail not found")
            FunctionResult.Error("Пользователь с таким email не найден")
        } catch (ex: Exception) {
            Log.e("sendResetPasswordEmail", "Error: ${ex.localizedMessage}")
            FunctionResult.Error("Произошла ошибка")
        }
    }

    private suspend fun reauthenticateUser(
        email: String,
        password: String
    ): FunctionResult<String> {
        val user = auth.currentUser
        return if (user != null) {
            try {
                user.reauthenticate(EmailAuthProvider.getCredential(email, password)).await()
                FunctionResult.Success("Reauthenticated successfully")
            } catch (ex: Exception) {
                Log.e("dataService", "$ex")
                FunctionResult.Error("$ex")
            }
        } else {
            FunctionResult.Error("User isn't authorized")
        }
    }

    //Тут email изменяется с помощью updateEmail, потому что в документе пользователя также хранится email.
    // По сути это рудиментарное хранение(вероятнее всего) и нужно бы заменить updateEmail -> verifyEmailBeforeUpdate
    // Для этого придется везде переписат логику получения пользователя и его email, поэтому я прям хз
    // Думаю просто можно переписать всю логику аутентификации пользователей в отдельный модуль и там уже работать со своей postgre базой, но это точно после финальной версии этого корявого проекта
    suspend fun changeUserData(user: User, oldEmail: String, password: String) : FunctionResult<String> {
        when (val reauthResult = reauthenticateUser(oldEmail, password)) {
            is FunctionResult.Success -> {
                try {
                    auth.currentUser?.updateEmail(user.email) // почему-то почта не меняется с использованием этого метода. Нужно глянуть в чем конкретно ошибка и вообще возможно что даун Я
                    Log.i("changeUserData", "")
                    this.updateUserDoc(user)
                    return FunctionResult.Success("User data updated Successfully")
                } catch (ex: Exception) {
                    return FunctionResult.Error("Some error happened with old email = $oldEmail, password = $password, newUser = $user")
                }
            }
            is FunctionResult.Error -> return FunctionResult.Error(reauthResult.message)
        }
    }
}