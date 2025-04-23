package com.example.sendingdata

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var tName: TextInputEditText
    lateinit var tEmail: TextInputEditText
    lateinit var tPhone: TextInputEditText
    lateinit var signUpButton :Button

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

        tName=findViewById(R.id.tiName)
        tEmail=findViewById(R.id.tiEmail)
        tPhone=findViewById(R.id.tiPhone)
        signUpButton=findViewById(R.id.button)

        signUpButton.setOnClickListener {
            var name :String= tName.text.toString()
            var email :String = tEmail.text.toString()
            var phone :Long = tPhone.text.toString().toLong()

            val intent : Intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("name",name)
            intent.putExtra("email",email)
            intent.putExtra("phone",phone)
            startActivity(intent)
        }
    }
}