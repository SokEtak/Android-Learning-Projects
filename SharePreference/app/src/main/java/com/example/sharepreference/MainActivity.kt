package com.example.sharepreference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var message: EditText
    lateinit var count: Button
    lateinit var remember: CheckBox
    lateinit var sharedPreferences: SharedPreferences

    var counter = 0
    var userName: String? = null
    var userMessage: String? = null
    var userRemember: Boolean = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        name = findViewById(R.id.name)
        message = findViewById(R.id.message)
        count = findViewById(R.id.button)
        remember = findViewById(R.id.checkBox)

        // Retrieve saved data
        retrieveData()

        count.setOnClickListener {
            counter++
            count.text = counter.toString()
            saveData()
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveData() {
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.putString("Key name", name.text.toString())
        editor.putString("Key message", message.text.toString())
        editor.putInt("Key count", counter)
        editor.putBoolean("Key remember", remember.isChecked)  // Fixed Key
        editor.apply()
    }

    @SuppressLint("SetTextI18n")
    private fun retrieveData() {
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        userName = sharedPreferences.getString("Key name", "")
        userMessage = sharedPreferences.getString("Key message", "")
        counter = sharedPreferences.getInt("Key count", 0) // Fixed Key
        userRemember = sharedPreferences.getBoolean("Key remember", false)

        name.setText(userName ?: "")
        message.setText(userMessage ?: "")
        count.text = counter.toString()
        remember.isChecked = userRemember
    }
}
