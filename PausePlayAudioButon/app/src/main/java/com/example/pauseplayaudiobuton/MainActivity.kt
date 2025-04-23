package com.example.pauseplayaudiobuton

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var imgIndex = 0 // 0 for play, 1 for pause
    lateinit var btnState: TextView // Declare btnState here

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize btnState here after the content view is set
        btnState = findViewById(R.id.buttonState)

        // Restore the state if available
        if (savedInstanceState != null) {
            imgIndex = savedInstanceState.getInt("imgIndex", 0) // Restore imgIndex
        }

        // Set padding to avoid system bars (optional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Lifecycle message for onCreate
        Toast.makeText(this, "onCreate: Activity Created", Toast.LENGTH_SHORT).show()

        // Update the state and button icon based on imgIndex
        updateButtonState()
    }

    // Save the state when the activity is paused or rotated
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("imgIndex", imgIndex) // Save the current state (play or pause)
    }

    // Restore the state after rotation
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        imgIndex = savedInstanceState.getInt("imgIndex", 0) // Restore the saved state
        updateButtonState() // Update the UI based on restored state
    }

    override fun onStart() {
        super.onStart()
        // Lifecycle message for onStart
        Toast.makeText(this, "onStart: Activity Started", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        // Lifecycle message for onResume
        Toast.makeText(this, "onResume: Activity Resumed", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        // Lifecycle message for onPause
        Toast.makeText(this, "onPause: Activity Paused", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        // Lifecycle message for onStop
        Toast.makeText(this, "onStop: Activity Stopped", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Lifecycle message for onDestroy
        Toast.makeText(this, "onDestroy: Activity Destroyed", Toast.LENGTH_SHORT).show()
    }

    // This method changes the icon when clicked
    @SuppressLint("ResourceType", "SetTextI18n")
    fun changeIcon(view: View?) {
        val imgButton = findViewById<ImageButton>(R.id.imageButton)
        val state: String

        if (imgIndex == 0) { // if currently paused, change it to playing
            imgIndex = 1 // set to paused state
            state = "Playing"
            btnState.text = "State: $state"
            btnState.setTextColor(Color.BLUE) // Change text color to blue for playing
            imgButton.setImageResource(R.drawable.baseline_pause_24) // Set to pause icon
        } else { // if currently playing, change it to paused
            imgIndex = 0 // set to play state
            state = "Paused"
            btnState.text = "State: $state"
            btnState.setTextColor(Color.RED) // Change text color to red for paused
            imgButton.setImageResource(R.drawable.baseline_play_arrow_24) // Set to play icon
        }
    }

    // Call this method when the button is clicked to toggle play/pause
    fun play(view: View) {
        changeIcon(view)
    }

    // Helper method to update button and text state based on imgIndex
    private fun updateButtonState() {
        val imgButton = findViewById<ImageButton>(R.id.imageButton)
        val state: String
        if (imgIndex == 0) {
            state = "Paused"
            btnState.text = "State: $state"
            btnState.setTextColor(Color.RED)
            imgButton.setImageResource(R.drawable.baseline_play_arrow_24) // Play icon
        } else {
            state = "Playing"
            btnState.text = "State: $state"
            btnState.setTextColor(Color.BLUE)
            imgButton.setImageResource(R.drawable.baseline_pause_24) // Pause icon
        }
    }
}
