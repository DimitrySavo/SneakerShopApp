package com.example.sneakershopapp.model

import android.util.Log
import androidx.compose.runtime.currentComposer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class DataService{
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    suspend fun getShoes(): List<Shoe> {
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

        return shoes
    }

    suspend fun registerUser(user: User, password: String) {
        try {
            val authResult = auth.createUserWithEmailAndPassword(user.email, password).await()
            val currentUserUid = authResult.user?.uid ?: throw IllegalStateException("User is null")

            db.collection("users").document(currentUserUid).set(user).await()

            Log.i("DataService", "registerUser: $currentUserUid")
        } catch (e: Exception) {
            Log.e("DataService", "registerUser: ${e.message}")
        }

    }

    suspend fun loginUser(email: String, password: String) {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Log.i("DataService", "loginUser: $email")
        } catch (e: Exception) {
            Log.e("DataService", "loginUser: ${e.message}")
        }
    }

    fun logoutUser() {
        auth.signOut()
    }

    fun getUserUid(): String {
        return  auth.currentUser?.uid ?: "User is null"
    }
}