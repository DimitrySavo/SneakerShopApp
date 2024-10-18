package com.example.sneakershopapp.utils

object FormattingUtils {
    fun formatLongTimerToMinSec(timerValue: Long) : String {
        return String.format("%02d:%02d", (timerValue / 1000) / 60, (timerValue / 1000) % 60)
    }
}