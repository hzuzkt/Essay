package com.example.myapplication_01

import com.example.myapplication.TimerViewModel

class PausedStatus(private val viewModel: TimerViewModel) : IStatus {
    override fun startButtonDisplayString() = "Resume"
    override fun clickStartButton() = viewModel.animatorController.resume()
    override fun stopButtonEnabled() = true
    override fun clickStopButton() = viewModel.animatorController.stop()
    override fun showEditText() = false
    override fun progressSweepAngle() = viewModel.timeLeft * 1.0f / viewModel.totalTime * 360
}
