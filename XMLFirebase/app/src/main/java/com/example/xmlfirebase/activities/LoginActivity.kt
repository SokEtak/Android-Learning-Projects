package com.example.xmlfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.xmlfirebase.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        auth = FirebaseAuth.getInstance()

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(loginBinding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        onBtnLoginClickListener()
        onBtnSignUpClickListener()
    }

    private fun onBtnSignUpClickListener(){
        loginBinding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onBtnLoginClickListener(){
        loginBinding.btnLogin.setOnClickListener {
            val email = loginBinding.edtLoginEmail.text.toString().trim()
            val password = loginBinding.edtLoginPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(baseContext, "Login Successful!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish() // Finish LoginActivity after successful login
                    } else {
                        when (task.exception) {
                            is FirebaseAuthInvalidUserException -> {
                                val errorCode = (task.exception as FirebaseAuthInvalidUserException).errorCode
                                if (errorCode == "ERROR_USER_NOT_FOUND" || errorCode == "user-not-found") {
                                    Toast.makeText(
                                        baseContext,
                                        "User with this email does not exist. Please sign up.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else if (errorCode == "ERROR_USER_DISABLED" || errorCode == "user-disabled") {
                                    Toast.makeText(
                                        baseContext,
                                        "This account has been disabled. Please contact support.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        baseContext,
                                        "Authentication failed: ${task.exception?.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                            is FirebaseAuthInvalidCredentialsException -> {
                                Toast.makeText(
                                    baseContext,
                                    "Invalid password. Please try again.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            else -> {
                                Toast.makeText(
                                    baseContext,
                                    "Authentication failed: ${task.exception?.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser

        currentUser?.reload()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (currentUser.isEmailVerified) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Please verify your email first.", Toast.LENGTH_SHORT).show()
                    auth.signOut()
                }
            } else {
                // Reload failed — account may be deleted/disabled
                auth.signOut()
                Toast.makeText(this, "Session expired or account invalid.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
