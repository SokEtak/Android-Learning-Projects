package com.example.audiocontroll

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var media: MediaPlayer
    private lateinit var seekBar: SeekBar
    private lateinit var handler: Handler
    private var isUserSeeking = false  // Flag to indicate if the user is dragging the SeekBar
    private lateinit var stateTextView: TextView  // Reference to the TextView showing the state
    private lateinit var timeTextView: TextView  // Reference to the TextView showing current time and duration

    // Save the current position and play state
    private var currentPosition: Int = 0
    private var isPlaying: Boolean = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize the stateTextView and timeTextView
        stateTextView = findViewById(R.id.stateTextView)
        timeTextView = findViewById(R.id.timeTextView)

        // Log and update the state text when activity is created
        Log.d("MainActivity", "onCreate: Activity Created")
        updateStateText("Idle")

        // Initialize media player only if it is not already initialized
        if (!::media.isInitialized) {
            media = MediaPlayer.create(this, R.raw.apt)
        }

        seekBar = findViewById(R.id.seekBar3)
        seekBar.max = media.duration

        handler = Handler(Looper.getMainLooper())

        // Set up the SeekBar listener for dragging
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    // When user drags the SeekBar, update the media player's position immediately
                    media.seekTo(progress)
                    Log.d("MainActivity", "onProgressChanged: Audio seeked to $progress ms")
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Set flag to true when user starts dragging
                isUserSeeking = true
                Log.d("MainActivity", "onStartTrackingTouch: Started dragging the SeekBar")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Set flag to false when user stops dragging
                isUserSeeking = false
                Log.d("MainActivity", "onStopTrackingTouch: Stopped dragging the SeekBar")
            }
        })

        // Set up listener to detect when song ends
        media.setOnCompletionListener {
            Log.d("MainActivity", "onCompletion: Song has ended")
            updateStateText("Idle")
            showToast("Audio playback finished")
        }

        // Restore the state if available
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("currentPosition", 0)
            isPlaying = savedInstanceState.getBoolean("isPlaying", false)

            media.seekTo(currentPosition)  // Restore the media position
            if (isPlaying) {
                media.start()  // If the state was playing, start playing again
                updateStateText("Playing")
                handler.postDelayed(UpdateSeekBar(), 1000)  // Continue updating the SeekBar
            } else {
                updateStateText("Paused")
            }
        } else {
            // If no saved state, set to idle
            media.seekTo(0)
        }

        // Ensure handler runs only once
        if (media.isPlaying) {
            handler.postDelayed(UpdateSeekBar(), 1000)
        }
    }

    // Save the state when the activity is paused or rotated
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentPosition", media.currentPosition) // Save current media position
        outState.putBoolean("isPlaying", media.isPlaying) // Save the play/pause state
    }

    // Update the state text view to show the current status
    private fun updateStateText(state: String) {
        stateTextView.text = state
    }

    // Show a Toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Format time in MM:SS format
    @SuppressLint("DefaultLocale")
    private fun formatTime(milliseconds: Int): String {
        val seconds = milliseconds / 1000
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }

    fun playAudio(view: View) {
        if (!media.isPlaying) {
            media.start()
            Log.d("MainActivity", "playAudio: Audio started playing")
            updateStateText("Playing")
            showToast("Audio started playing")  // Show Toast message when playing starts
            handler.postDelayed(UpdateSeekBar(), 1000)  // Start updating the SeekBar when the audio starts
        }
    }

    fun pauseAudio(view: View) {
        if (media.isPlaying) {
            media.pause()
            Log.d("MainActivity", "pauseAudio: Audio paused")
            updateStateText("Paused")
            showToast("Audio paused")  // Show Toast message when paused
            handler.removeCallbacks(UpdateSeekBar())  // Stop updating the SeekBar when the audio is paused
        }
    }

    fun doNext(view: View) {
        val addTime = 5000
        val cur = media.currentPosition
        val duration = media.duration
        if (cur + addTime < duration) {
            media.seekTo(cur + addTime)
            Log.d("MainActivity", "doNext: Audio skipped forward 5 seconds")
            showToast("Skipped forward 5 seconds")  // Show Toast message when skipping forward
        } else {
            Log.d("MainActivity", "doNext: Cannot skip forward 5 seconds, end of audio")
            showToast("Cannot skip forward, end of audio")  // Show Toast message if at the end
        }
    }

    fun rewind(view: View) {
        val subTime = 5000
        val cur = media.currentPosition
        if (cur - subTime > 0) {
            media.seekTo(cur - subTime)
            Log.d("MainActivity", "rewind: Audio rewound 5 seconds")
            showToast("Rewound 5 seconds")  // Show Toast message when rewound
        } else {
            media.seekTo(0)
            Log.d("MainActivity", "rewind: Reached beginning of the audio")
            showToast("Reached beginning of the audio")  // Show Toast message when at the start
        }
    }

    private inner class UpdateSeekBar : Runnable {
        @SuppressLint("SetTextI18n")
        override fun run() {
            if (!isUserSeeking && media.isPlaying) {
                val currentPosition = media.currentPosition
                seekBar.progress = currentPosition
                val formattedCurrentTime = formatTime(currentPosition)
                val formattedDuration = formatTime(media.duration)
                timeTextView.text = "$formattedCurrentTime / $formattedDuration" // Update the time TextView

                // Instead of posting every second, use postOnAnimation to make it more efficient
                handler.postDelayed(this, 1000)
            }
        }
    }
}
