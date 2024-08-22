package com.example.sneakershopapp.model

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude

data class CartShoe(
    @get:Exclude var documentId: String? = null,
    val size: String,
    val quantity: Long = 1,
    var shoeRef: DocumentReference? = null
)
