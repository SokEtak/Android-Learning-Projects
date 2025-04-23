package com.example.viewbinding

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable Edge-to-edge (if needed)
        enableEdgeToEdge()

        // Initialize ViewBinding
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root  // This is the root view for the layout

        // Set the content view to the root view of the binding (not R.layout.activity_main)
        setContentView(view)
        // Set up window insets listener for edge-to-edge compatibility
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set button click listener
        mainBinding.btn.setOnClickListener {
            val name: String = mainBinding.editText.text.toString()
            mainBinding.tvResult.text = name  // Set the text of the TextView
        }
    }
}
