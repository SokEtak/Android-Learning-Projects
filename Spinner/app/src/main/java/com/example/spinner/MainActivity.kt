package com.example.spinner

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var tv :TextView
    lateinit var spinner: Spinner
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
        tv = findViewById(R.id.tv)
        spinner = findViewById(R.id.spinner)
        spinner.setBackgroundColor(R.drawable.spinner_outline)

        val colors = listOf(
            "Red", "Green", "Blue", "Yellow", "Purple", "Orange", "Cyan", "Magenta", "Pink",
            "Teal", "Lime", "Indigo", "Brown", "Violet", "Gray", "Black", "White", "Beige", "Coral",
            "Chocolate", "DarkBlue", "DarkGreen", "DarkGray", "DeepPink", "Firebrick", "Gold",
            "HotPink", "LightBlue", "LightGray", "LightGreen", "LightPink", "MediumOrchid",
            "MediumPurple", "RoyalBlue", "SaddleBrown"
        )


        val  arrayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,colors)

        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            @SuppressLint("SetTextI18n")
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                tv.text = "item selected:"+colors[position]

                when (colors[position]) {
                    "Red" -> tv.setTextColor(Color.RED)
                    "Green" -> tv.setTextColor(Color.GREEN)
                    "Blue" -> tv.setTextColor(Color.BLUE)
                    "Yellow" -> tv.setTextColor(Color.YELLOW)
                    "Purple" -> tv.setTextColor(Color.parseColor("#800080"))
                    "Orange" -> tv.setTextColor(Color.parseColor("#FFA500"))
                    "Cyan" -> tv.setTextColor(Color.CYAN)
                    "Magenta" -> tv.setTextColor(Color.MAGENTA)
                    "Pink" -> tv.setTextColor(Color.parseColor("#FFC0CB"))
                    "Teal" -> tv.setTextColor(Color.parseColor("#008080"))
                    "Lime" -> tv.setTextColor(Color.parseColor("#00FF00"))
                    "Indigo" -> tv.setTextColor(Color.parseColor("#4B0082"))
                    "Brown" -> tv.setTextColor(Color.parseColor("#A52A2A"))
                    "Violet" -> tv.setTextColor(Color.parseColor("#EE82EE"))
                    "Gray" -> tv.setTextColor(Color.GRAY)
                    "Black" -> tv.setTextColor(Color.BLACK)
                    "White" -> tv.setTextColor(Color.WHITE)
                    "Beige" -> tv.setTextColor(Color.parseColor("#F5F5DC"))
                    "Coral" -> tv.setTextColor(Color.parseColor("#FF7F50"))
                    "Chocolate" -> tv.setTextColor(Color.parseColor("#D2691E"))
                    "DarkBlue" -> tv.setTextColor(Color.parseColor("#00008B"))
                    "DarkGreen" -> tv.setTextColor(Color.parseColor("#006400"))
                    "DarkGray" -> tv.setTextColor(Color.parseColor("#A9A9A9"))
                    "DeepPink" -> tv.setTextColor(Color.parseColor("#FF1493"))
                    "Firebrick" -> tv.setTextColor(Color.parseColor("#B22222"))
                    "Gold" -> tv.setTextColor(Color.parseColor("#FFD700"))
                    "HotPink" -> tv.setTextColor(Color.parseColor("#FF69B4"))
                    "LightBlue" -> tv.setTextColor(Color.parseColor("#ADD8E6"))
                    "LightGray" -> tv.setTextColor(Color.parseColor("#D3D3D3"))
                    "LightGreen" -> tv.setTextColor(Color.parseColor("#90EE90"))
                    "LightPink" -> tv.setTextColor(Color.parseColor("#FFB6C1"))
                    "MediumOrchid" -> tv.setTextColor(Color.parseColor("#BA55D3"))
                    "MediumPurple" -> tv.setTextColor(Color.parseColor("#9370DB"))
                    "RoyalBlue" -> tv.setTextColor(Color.parseColor("#4169E1"))
                    "SaddleBrown" -> tv.setTextColor(Color.parseColor("#8B4513"))
                    else -> tv.setTextColor(Color.BLACK)  // Default color
                }

                Toast.makeText(this@MainActivity,"item selected:"+colors[position],Toast.LENGTH_SHORT).show()
            }

            @SuppressLint("SetTextI18n")
            override fun onNothingSelected(parent: AdapterView<*>?) {
                tv.text = "no item selected"
                Toast.makeText(this@MainActivity,"no item selected!!!",Toast.LENGTH_SHORT).show()
            }
        }

    }
}