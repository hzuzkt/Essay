package com.example.myapplication_01

import com.example.myapplication.TimerViewModel

class CompletedStatus(private val viewModel: TimerViewModel) : IStatus {
    override fun startButtonDisplayString() = "Start"
    override fun clickStartButton() = viewModel.animatorController.start()
    override fun stopButtonEnabled() = false
    override fun clickStopButton() {}
    override fun showEditText() = true
}
