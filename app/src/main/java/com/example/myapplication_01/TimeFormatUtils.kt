package com.example.myapplication_01

object TimeFormatUtils {
    fun formatTime(time: Long): String {
        var value = time
        val seconds = value % 60
        value /= 60
        val minutes = value % 60
        value /= 60
        val hours = value % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}