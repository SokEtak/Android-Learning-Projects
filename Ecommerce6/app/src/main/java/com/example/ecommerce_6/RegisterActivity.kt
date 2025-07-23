package com.example.ecommerce_6

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerce_6.databinding.ActivityRegisterBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding // View Binding instance
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Finish RegisterActivity so user can't go back to it
        }
    }

    private fun registerUser() {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val passwordConfirmation = binding.etPasswordConfirmation.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != passwordConfirmation) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        val registerRequest = RegisterRequest(name, email, password, passwordConfirmation)

        RetrofitClient.instance.register(registerRequest)
            .enqueue(object : Callback<ApiResponse<Any>> {
                override fun onResponse(call: Call<ApiResponse<Any>>, response: Response<ApiResponse<Any>>) {
                    if (response.isSuccessful) {
                        val apiResponse = response.body()
                        apiResponse?.let {
                            sessionManager.saveAuthToken(it.token ?: "") // Save token
                            sessionManager.saveUserEmail(it.user?.email ?: email) // Save email

                            Toast.makeText(this@RegisterActivity, it.message, Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                            finish() // Close register activity
                        }
                    } else {
                        val errorResponse = response.errorBody()?.string()
                        val gson = com.google.gson.Gson()
                        try {
                            val errorApi = gson.fromJson(errorResponse, ApiResponse::class.java)
                            val errorMessage = errorApi.message ?: "Unknown error"
                            val errors = errorApi.errors
                            var fullMessage = errorMessage
                            errors?.forEach { (field, messages) ->
                                fullMessage += "\n- $field: ${messages.joinToString(", ")}"
                            }
                            Toast.makeText(this@RegisterActivity, fullMessage, Toast.LENGTH_LONG).show()
                        } catch (e: Exception) {
                            Toast.makeText(this@RegisterActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<ApiResponse<Any>>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Network Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
    }
}