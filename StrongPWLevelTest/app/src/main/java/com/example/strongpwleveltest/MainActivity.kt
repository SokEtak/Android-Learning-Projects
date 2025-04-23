package com.example.strongpwleveltest

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var textInputUsername: TextInputEditText
    private lateinit var tv: TextView

    private lateinit var editTextPassword: EditText
    private lateinit var editTextRePassword: EditText
    private lateinit var signUpBtn: Button
    lateinit var progressBar: ProgressBar


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

        textInputUsername = findViewById(R.id.textInputUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextRePassword = findViewById(R.id.editTextRePassword)
        signUpBtn = findViewById(R.id.button)
        tv = findViewById(R.id.tv2)
//        progressBar = findViewById(R.id.progressBar)

        signUpBtn.setOnClickListener {
            val userNameInput = textInputUsername.text.toString()
            val passwordInput = editTextPassword.text.toString()
            val rePasswordInput = editTextRePassword.text.toString()
            val containsNumber = Regex(".*[0-9].*") // Regex to check for at least one number
            val strongPLevel : Int = 0


            // Reset visibility and text for the error message
            tv.visibility = View.INVISIBLE

            // Validate inputs
            when {

                userNameInput.isNotEmpty() && !userNameInput[0].isUpperCase() -> {
                    tv.text = "The first letter must be uppercase!"
                }
                !userNameInput.contains('@') -> {
                    tv.text = "This is not a valid email! It must contain '@'."
                }
                !containsNumber.matches(userNameInput) -> {
                    tv.text = "The email must contain at least one number!"
                }
                userNameInput.count { it.isDigit() } > 6 -> {
                    tv.text = "The email must not contain more than 6 digits!"
                }
                passwordInput.length < 8 -> {
                    tv.text = "Password must be at least 8 characters long!"
                }
                passwordInput != rePasswordInput -> {
                    tv.text = "Passwords do not match!"
                }
                else -> {
                    // All checks passed
                    tv.text = "Success!" // Set success message
                    tv.visibility = View.VISIBLE // Show success message
                    // Optionally, clear the input fields here
                    textInputUsername.text?.clear()
                    editTextPassword.text?.clear()
                    editTextRePassword.text?.clear()
                    return@setOnClickListener // Exit after validation
                }
            }

            // Show error message if any validation fails
            tv.visibility = View.VISIBLE // Make sure to show the message if validation fails
        }





    }
}