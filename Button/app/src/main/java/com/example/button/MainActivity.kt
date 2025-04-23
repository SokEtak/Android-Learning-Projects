package com.example.button

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var textTV :TextView
    lateinit var changeTextColorBtn :Button
    lateinit var makeInvisbleBTn :Button
    lateinit var randomNumberBtn : Button
    lateinit var tempSatring :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textTV = findViewById(R.id.tv)
        changeTextColorBtn = findViewById(R.id.changeTxtBtn)
        makeInvisbleBTn = findViewById(R.id.makeVisiblyBtn)

        changeTextColorBtn.setOnClickListener{
            textTV.setTextColor(Color.RED)
            makeInvisbleBTn.visibility=View.VISIBLE

        }
        makeInvisbleBTn.setOnClickListener{
            textTV.isVisible=false
        }

        randomNumberBtn.setOnClickListener {
            textTV.text = randomNumberBtn.toString()
        }

        fun randomNumber() : Int
        {
            return Random.nextInt(101)
        }
    }
}