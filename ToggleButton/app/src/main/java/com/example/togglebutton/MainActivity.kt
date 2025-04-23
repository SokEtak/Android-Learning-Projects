package com.example.togglebutton

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var toggleButton:ToggleButton
    lateinit var imgV1:ImageView
    lateinit var imgV2:ImageView
    lateinit var tv : TextView
    var staus :String = "on"

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
        toggleButton = findViewById(R.id.tB)
        imgV1 = findViewById(R.id.imgV)
        imgV2 = findViewById(R.id.ImgV2)
        imgV2.setImageResource(R.drawable.light_on)
        tv= findViewById(R.id.textView)

        toggleButton.setOnClickListener {

            if (toggleButton.isChecked) {
                staus = "off"
                imgV1.visibility = View.INVISIBLE
                imgV2.visibility = View.VISIBLE
                tv.text = "status:On"

            }else{staus = "off"
                imgV1.visibility = View.VISIBLE
                imgV2.visibility = View.INVISIBLE
                tv.text = "status:Off"
            }
        }
    }
}