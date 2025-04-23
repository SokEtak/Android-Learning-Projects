package com.example.edittextdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Comment

class MainActivity : AppCompatActivity() {
    private lateinit var userNameInput : EditText
    private lateinit var userPwInput : EditText
    private lateinit var userRePwInput : EditText
    private lateinit var userComment : EditText
    private lateinit var tv : TextView
    private lateinit var subBtn : TextView

    @SuppressLint("CutPasteId", "MissingInflatedId", "SetTextI18n")
    override fun  onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userNameInput = findViewById(R.id.editTextUsername)
        userPwInput = findViewById(R.id.editTextNumberPassword)
        userRePwInput = findViewById(R.id.editTextNumberRePassword)
        userComment = findViewById(R.id.editTextTextMultiLine)

        tv = findViewById(R.id.textView)
        subBtn = findViewById(R.id.submitButton)

        subBtn.setOnClickListener {
            val txt =  "username:${userNameInput.text}${userPwInput.text},${userRePwInput.text},${userComment.text}"

            tv.text= txt
        }
    }
}