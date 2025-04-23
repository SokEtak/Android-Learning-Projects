package com.example.countdownappusable

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var tv: TextView
    lateinit var pause: Button
    lateinit var start: Button
    lateinit var countDownTimer: CountDownTimer
    var duration: Long = 10000 // Countdown duration in milliseconds (10 seconds)
    var timeLeft: Long = duration // Variable to track remaining time

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tv = findViewById(R.id.textView)
        pause = findViewById(R.id.pauseButton)
        start = findViewById(R.id.playBtn)

        // Initialize the countdown timer
        initCountDownTimer(timeLeft)

        // Start button click listener
        start.setOnClickListener {
            play()
        }

        // Pause button click listener
        pause.setOnClickListener {
            pause()
        }

        // Apply edge-to-edge insets (if required for your layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Initialize the countdown timer with the remaining time
    fun initCountDownTimer(timeRemaining: Long) {
        countDownTimer = object : CountDownTimer(timeRemaining, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Update the TextView with the remaining seconds
                tv.text = (millisUntilFinished / 1000).toString()
                timeLeft = millisUntilFinished // Update the remaining time
            }

            override fun onFinish() {
                // Show a Toast when the timer finishes
                Toast.makeText(this@MainActivity, "Happy New Year", Toast.LENGTH_SHORT).show()
                tv.text = "0" // Show 0 when the timer finishes
            }
        }
    }

    // Pause the countdown
    fun pause() {
        countDownTimer.cancel() // Cancel the ongoing countdown
    }

    // Play/start or resume the countdown
    fun play() {
        // Start the countdown with the remaining time
        initCountDownTimer(timeLeft)
        countDownTimer.start() // Start/resume the countdown from the remaining time
    }
}
