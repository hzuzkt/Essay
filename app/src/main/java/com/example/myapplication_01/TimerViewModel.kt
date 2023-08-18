package com.example.myapplication
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication_01.CompletedStatus
import com.example.myapplication_01.IStatus
import com.example.myapplication_01.NotStartedStatus

const val MAX_INPUT_LENGTH = 5

class TimerViewModel : ViewModel() {
    var status: IStatus by mutableStateOf(NotStartedStatus(this))
    var animatorController = AnimatorController(this)
    var totalTime: Long by mutableStateOf(0L)
    var timeLeft: Long by mutableStateOf(0L)
    fun updateValue(text: String) {
        if (status is CompletedStatus) status = NotStartedStatus(this)
        if (text.length > MAX_INPUT_LENGTH) return
        var value = text.replace("\\D".toRegex(),"")
        if(value.startsWith("0")) value = value.substring(1)
        if (value.isBlank()) value = "0"
        totalTime = value.toLong()
        timeLeft = value.toLong()
    }

}
