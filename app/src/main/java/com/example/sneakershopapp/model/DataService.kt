package com.example.sneakershopapp.model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.getField
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

    suspend fun markShoeAsFavorite(shoeId: String){
        val currentUserUid = auth.currentUser?.uid ?: throw IllegalStateException("User is null")
        val userRef = db.collection("users").document(currentUserUid)
        userRef.update("favorites", FieldValue.arrayUnion(shoeId)).await()
    }

    suspend fun unmarkShoeAsFavorite(shoeId: String){
        val currentUserUid = auth.currentUser?.uid ?: throw IllegalStateException("User is null")
        val userRef = db.collection("users").document(currentUserUid)
        userRef.update("favorites", FieldValue.arrayRemove(shoeId)).await()
    }

    suspend fun getFavorites(): List<String> {
        val currentUserUid = auth.currentUser?.uid ?: throw IllegalStateException("User is null")
        val userSnapshot = db.collection("users").document(currentUserUid).get().await()
        val user = userSnapshot.toObject<User>()
        return user?.favorites ?: emptyList()
    }

    suspend fun addToCart(shoeId: String, shoe: CartShoe) {
        val currentUserUid = auth.currentUser?.uid ?: throw IllegalStateException("User is null")
        val shoeRef = db.collection("Shoes").document(shoeId).get().await()
        if(shoeRef.exists().not()){
            throw IllegalStateException("Shoe not found")
        } else if (shoeRef.toObject<Shoe>()?.sizes?.get(shoe.size)?.inStock == 0L){
            throw IllegalStateException("Shoe is out of stock") // should replace with some better callbacks but for now it's ok
        }
        shoe.shoeRef = shoeRef.reference
        db.collection("users").document(currentUserUid).collection("cart").add(shoe).await() // cant use cloud functions because of free plan so I have to do it on client side I guess
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

    fun getUserUid(): String? {
        return auth.currentUser?.uid
    }
}