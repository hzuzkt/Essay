package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator

class AnimatorController(private val viewModel: TimerViewModel) {
    private var valueAnimator: ValueAnimator? = null
    fun start() {
        if (viewModel.totalTime == 0L) return
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofInt(viewModel.totalTime.toInt(), 0)
            valueAnimator?.interpolator = LinearInterpolator()
            valueAnimator?.addUpdateListener {
                viewModel.timeLeft = (it.animatedValue as Int).toLong()
            }
            valueAnimator?.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    complete()
                }
            })
        } else {
            valueAnimator?.setIntValues(viewModel.totalTime.toInt(), 0)
        }
        valueAnimator?.duration = viewModel.totalTime * 1000L
        valueAnimator?.start()
    }

    fun pause() {
        valueAnimator?.pause()
    }

    fun resume() {
        valueAnimator?.resume()
    }

    fun stop() {
        valueAnimator?.cancel()
        viewModel.timeLeft = 0
    }

    fun complete() {
        viewModel.totalTime = 0
    }


}
