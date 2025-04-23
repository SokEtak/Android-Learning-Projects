package com.example.activitylifecyclenotapplicationlifecycle

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var increase_btn: Button
    lateinit var goToActivity2Btn: Button
    var counter = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textView = findViewById(R.id.tvDisplayNum)
        increase_btn = findViewById(R.id.increase_btn)
        goToActivity2Btn = findViewById(R.id.go_Activity2_Btn)

        increase_btn.setOnClickListener {
            increaseNumber()
        }
        goToActivity2Btn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            // Start MainActivity2
            startActivity(intent)
        }
        Log.d("MainActivityLifecycle", "MainActivity created")
    }

    private fun increaseNumber() {
        counter += 1
        textView.text = counter.toString()
    }

    override fun onStart() {
        Log.d("MainActivityLifecycle", "onStart")
        super.onStart()
    }

    override fun onRestart() {
        Log.d("MainActivityLifecycle", "onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Log.d("MainActivityLifecycle", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivityLifecycle", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivityLifecycle", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivityLifecycle", "onDestroy")
        super.onDestroy()
    }
}
