package com.example.xmlfirebase.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.xmlfirebase.R
import com.example.xmlfirebase.databinding.ActivityUpdateUserBinding
import com.google.firebase.database.FirebaseDatabase

class UpdateUserActivity : AppCompatActivity() {
    private lateinit var updateUserBinding: ActivityUpdateUserBinding
    // CORRECTED: Changed "users" to "User" to match MainActivity's database path
    private val database = FirebaseDatabase.getInstance()
    private val reference = database.getReference("User") // <-- THIS LINE WAS MODIFIED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        updateUserBinding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(updateUserBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve passed data
        val userId = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        val email = intent.getStringExtra("email")

        // Fill EditTexts
        updateUserBinding.edtUpdateName.setText(name)
        updateUserBinding.edtUpdateAge.setText(age)
        updateUserBinding.edtUpdateEmail.setText(email)

        // Button click to update Firebase
        updateUserBinding.upBtn.setOnClickListener {
            val newName = updateUserBinding.edtUpdateName.text.toString()
            val newAge = updateUserBinding.edtUpdateAge.text.toString()
            val newEmail = updateUserBinding.edtUpdateEmail.text.toString()

            if (userId != null) {
                val userUpdates = mapOf(
                    "userName" to newName,
                    "userAge" to newAge,
                    "userEmail" to newEmail
                )

                reference.child(userId).updateChildren(userUpdates).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Failed to update user", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "User ID is missing", Toast.LENGTH_SHORT).show()
            }
        }
    }
}