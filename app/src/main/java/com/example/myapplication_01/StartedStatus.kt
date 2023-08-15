package com.example.myapplication_01

import com.example.myapplication.TimerViewModel

class StartedStatus(private val viewModel: TimerViewModel) : IStatus {
    override fun startButtonDisplayString() = "Pause"
    override fun clickStartButton() = viewModel.animatorController.pause()
    override fun stopButtonEnabled() = true
    override fun clickStopButton() = viewModel.animatorController.stop()
    override fun showEditText() = false
}
