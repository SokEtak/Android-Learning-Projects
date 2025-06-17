package com.example.xmlfirebase

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.xmlfirebase.databinding.ActivityAddUserBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddUserActivity : AppCompatActivity() {
    lateinit var addUserBinding: ActivityAddUserBinding
      val  database: FirebaseDatabase = FirebaseDatabase.getInstance()
      val  reference: DatabaseReference = database.reference.child("User")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        addUserBinding = ActivityAddUserBinding.inflate(layoutInflater)
        val view = addUserBinding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTexts = listOf<EditText>(
            addUserBinding.edtAddName,
            addUserBinding.edtAddAge,
            addUserBinding.edtAddEmail
        )

        fun updateButtonState() {
            val allFilled = editTexts.all { it.text.toString().trim().isNotEmpty() }
            addUserBinding.addBtn.isEnabled = allFilled
        }

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updateButtonState()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

    // Add the watcher to each EditText
        editTexts.forEach { it.addTextChangedListener(textWatcher) }

    // Run initial check
        updateButtonState()

        addUserToDatabase()
    }

    private fun addUserToDatabase() {
        addUserBinding.addBtn.setOnClickListener {
            val name: String = addUserBinding.edtAddName.text.toString()
            val age: String = addUserBinding.edtAddAge.text.toString()
            val email: String = addUserBinding.edtAddEmail.text.toString()
            val id: String = reference.push().key.toString()

            val user = User(id, name, age, email)

            reference.child(id).setValue(user)
                .addOnCompleteListener {
                    Toast.makeText(this, "New Item Added Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to Add New Item", Toast.LENGTH_SHORT).show()
                }
        }
    }
}