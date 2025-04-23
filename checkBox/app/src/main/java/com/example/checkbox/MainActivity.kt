package com.example.checkbox

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var femaleCB : CheckBox
    lateinit var maleCB : CheckBox
    lateinit var tv : TextView
    lateinit var  btn : Button
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        femaleCB  = findViewById(R.id.femaleCB)
        maleCB  = findViewById(R.id.maleCB)
        tv   = findViewById(R.id.textView)
        btn = findViewById(R.id.button)


        btn.setOnClickListener {
            var gender :String? = null
            if (femaleCB.isChecked){
                gender = "female"
            }else{
                gender = "male"
            }
            tv.text="your gender is : $gender"
        }




    }
}