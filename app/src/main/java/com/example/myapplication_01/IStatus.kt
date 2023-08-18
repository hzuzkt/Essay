package com.example.myapplication_01

interface IStatus {
    /**
     * The content string displayed in Start Button.
     * include: Start, Pause, Resume.
     */
    fun startButtonDisplayString(): String
    /**
     * The behaviour when click Start Button.
     */
    fun clickStartButton()
    /**
     * Stop Button enable status
     */
    fun stopButtonEnabled(): Boolean
    /**
     * The behaviour when click Stop Button.
     */
    fun clickStopButton()
    /**
     * Show or hide EditText
     */
    fun showEditText(): Boolean

    fun progressSweepAngle(): Float

    /**
     * Completed string
     */
    fun completedString(): String
}