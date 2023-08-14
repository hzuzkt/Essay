package com.example.myapplication
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

const val MAX_INPUT_LENGTH = 5

class TimerViewModel : ViewModel() {
    var animatorController = AnimatorController(this)
    var totalTime: Long by mutableStateOf(0L)
    var timeLeft: Long by mutableStateOf(0L)
    fun updateValue(text: String) {
        if (text.length > MAX_INPUT_LENGTH) return
        var value = text.replace("\\D".toRegex(),"")
        if(value.startsWith("0")) value = value.substring(1)
        if (value.isBlank()) value = "0"
        totalTime = value.toLong()
        timeLeft = value.toLong()
    }

}
