package com.example.sneakershopapp.utils

import android.content.Context
import android.net.Uri
import android.util.Base64

object FormattingUtils {
    fun formatLongTimerToMinSec(timerValue: Long) : String {
        return String.format("%02d:%02d", (timerValue / 1000) / 60, (timerValue / 1000) % 60)
    }

    fun getBase64FromUri(context: Context, uri: Uri): String? {
        return try {
            // Получаем InputStream из URI
            val inputStream = context.contentResolver.openInputStream(uri)
            val bytes = inputStream?.readBytes() // Считываем все байты
            inputStream?.close()

            // Кодируем байты в Base64
            if (bytes != null) {
                Base64.encodeToString(bytes, Base64.DEFAULT)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}