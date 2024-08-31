package com.example.sneakershopapp.model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.getField
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class  DataService{
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
        val userRef = db.collection("users").document(currentUserUid)
        userRef.update("favorites", FieldValue.arrayRemove(shoeId)).await()
        return FunctionResult.Success(Unit)
    }

    suspend fun getFavorites(): FunctionResult<List<String>> {
        val currentUserUid = auth.currentUser?.uid ?: return FunctionResult.Error("User is null")
        val userSnapshot = db.collection("users").document(currentUserUid).get().await()
        val user = userSnapshot.toObject<User>()
        return FunctionResult.Success(user?.favorites ?: emptyList())
    }

    suspend fun addToCart(shoeId: String, shoe: CartShoe): FunctionResult<Unit> {
        val currentUserUid = auth.currentUser?.uid ?: return FunctionResult.Error("User is null")
        val shoeRef = db.collection("Shoes").document(shoeId).get().await()
        if(shoeRef.exists().not()){
            return FunctionResult.Error("Shoe not found")
        } else if (shoeRef.toObject<Shoe>()?.sizes?.get(shoe.size)?.inStock == 0L){
            return FunctionResult.Error("Shoe is out of stock") // should replace with some better callbacks but for now it's ok
        }
        shoe.shoeRef = shoeRef.reference
        db.collection("users").document(currentUserUid).collection("cart").add(shoe).await() // cant use cloud functions because of free plan
        return FunctionResult.Success(Unit)
    }

    suspend fun registerUser(user: User, password: String): FunctionResult<String> {
        try {
            val authResult = auth.createUserWithEmailAndPassword(user.email, password).await()
            val currentUserUid = authResult.user?.uid ?: return FunctionResult.Error("User is null")

            db.collection("users").document(currentUserUid).set(user).await()

            Log.i("DataService", "registerUser: $currentUserUid")
            return FunctionResult.Success(currentUserUid)
        } catch (e: Exception) {
            Log.e("DataService", "registerUser: ${e.message}")
            return FunctionResult.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun loginUser(email: String, password: String): FunctionResult<Unit> {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Log.i("DataService", "loginUser: $email")
            return FunctionResult.Success(Unit)
        } catch (e: Exception) {
            val errorMessage = when(e){
                is FirebaseAuthException -> when(e.errorCode){
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
            if(auth.currentUser == null){
                FunctionResult.Success(null)
            } else {
                FunctionResult.Success(auth.currentUser!!.uid)
            }
        } catch (e: Exception) {
            FunctionResult.Error(e.toString())
        }
    }
}