package com.example.activitylifecyclenotapplicationlifecycle

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    lateinit var gotTOMainActivity: Button  // Declare the button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        gotTOMainActivity = findViewById(R.id.goToMainActivity_Btn)  // Fix: Initialize button
        // Set the onClickListener
        gotTOMainActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        Log.d("Message", "MainActivity2 created")
    }

    override fun onStart() {
        Log.d("message", "MainActivity2 onStart")
        super.onStart()
    }

    override fun onRestart() {
        Log.d("message", "MainActivity2 onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Log.d("message", "MainActivity2 onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("message", "MainActivity2 onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("message", "MainActivity2 OnStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("message", "MainActivity2 onDestroy")
        super.onDestroy()
    }
}
