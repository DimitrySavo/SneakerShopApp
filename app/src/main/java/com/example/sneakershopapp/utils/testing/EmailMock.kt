package com.example.sneakershopapp.utils.testing

import android.util.Log
import kotlinx.coroutines.delay

object EmailMock {
    suspend fun SendEmail(otpCode: String, userName: String, userEmailAddress: String) {
        Log.d("Mock Email: ", "Email send. Wait for receiving")
        delay((1000..9000).random().toLong())
        Log.d(
            "Mock Email",
            "Receiver: $userEmailAddress\nHello $userName!\nHere's your six number code for password reset: $otpCode\nIf you don't send request to reset password - ignore this email.\nSneakThrough Team."
        )
    }
}