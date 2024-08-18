package com.example.sneakershopapp.utils

object ValidationUtils {
    private val emailRegex = Regex("^[a-zA-Z0-9][a-zA-Z0-9._?*]{0,254}@[a-zA-Z0-9-]{1,63}+(\\.[a-z0-9-]{2,7})+$")
    private val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")

    fun isEmailValid(email: String): Boolean {
        return emailRegex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        return passwordRegex.matches(password)
    }

    fun isNameValid(name: String): Boolean {
        return name.isNotBlank()
    }

    fun isSurnameValid(surname: String): Boolean {
        return surname.isNotBlank()
    }
}