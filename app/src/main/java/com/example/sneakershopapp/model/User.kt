package com.example.sneakershopapp.model

import com.example.sneakershopapp.utils.ValidationUtils

data class User(
    val email: String,
    val name: String,
    val surname: String,
    val phoneNumber: String? = null,
    val deliveryAddress: String? = null,
    var favorites: List<String> = emptyList(),
){
    constructor(): this(
        "",
        "",
        ""
    )
}

//Дописать функцию перевода в map<String, Any?>

