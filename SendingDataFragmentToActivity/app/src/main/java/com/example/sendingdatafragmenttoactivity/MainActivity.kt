package com.example.sendingdatafragmenttoactivity

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    lateinit var tvName : TextView
    lateinit var tvEmail:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)

        var fragmentManager : FragmentManager = supportFragmentManager
        var fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction()
        var myFragment = MyFragment()
        fragmentTransaction.add(R.id.frame,myFragment)
        fragmentTransaction.commit()
    }
    fun takeData(username:String,email: String){
        tvName.text = "Name:${username}"
        tvEmail.text = "Email:${email}"
    }
}