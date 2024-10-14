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

    fun validateName(name: String): String {
        return when {
            name.isBlank() -> "Поле не может быть пустым"
            name.contains(Regex("[0-9]")) -> "Поле не может содержать цифр"
            else -> ""
        }
    }

    fun validateSurname(surname: String): String {
        return when {
            surname.isBlank() -> "Поле не может быть пустым"
            surname.contains(Regex("[0-9]")) -> "Поле не может содержать цифр"
            else -> ""
        }
    }

    fun validateEmail(email: String): String {
        return when {
            email.isBlank() -> "Поле не может быть пустым"
            !isEmailValid(email) -> "Неверный формат"
            else -> ""
        }
    }

    fun validatePassword(password: String): String {
        return when {
            password.isBlank() -> "Поле не может быть пустым"
            !isPasswordValid(password) -> "Пароль должен включать в себя:\n8 символов\n1 заглавный символ\n1 цифру"
            else -> ""
        }
    }
}