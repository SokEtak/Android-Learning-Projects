package com.example.intent

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var edtName : EditText
    lateinit var edtAge : EditText
    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        edtName  = findViewById(R.id.edtName)
        edtAge  = findViewById(R.id.edtAge)
        btn = findViewById(R.id.button)

        btn.setOnClickListener {
            val username :String = edtName.text.toString()
            val age: Int = edtAge.text.toString().toIntOrNull() ?: 0 // noted point
            val intent = Intent(this@MainActivity,MainActivity2::class.java)

            intent.putExtra("username",username)
            intent.putExtra("user_Age",age)
            startActivity(intent)
        }
    }

}