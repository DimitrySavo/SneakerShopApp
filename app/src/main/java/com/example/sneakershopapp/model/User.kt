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

fun User.checkUser(password: String): Boolean {
    return ValidationUtils.isEmailValid(this.email) &&
            ValidationUtils.isNameValid(this.name) &&
            ValidationUtils.isSurnameValid(this.surname) &&
            ValidationUtils.isPasswordValid(password)
}
