package com.example.radiobutton

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var rbRed : RadioButton
    lateinit var rbBlue : RadioButton
    lateinit var rbGreen : RadioButton
    lateinit var layout : ConstraintLayout
    lateinit var btn : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLay)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rbRed = findViewById(R.id.rbRed)
        rbGreen = findViewById(R.id.rbGreen)
        rbBlue = findViewById(R.id.rbBlue)
        layout = findViewById(R.id.mainLay)
        btn = findViewById(R.id.btn)

        btn.setOnClickListener {
            if(rbRed.isChecked){
                layout.setBackgroundColor(Color.RED)
            }else if(rbBlue.isChecked){
                 layout.setBackgroundColor(Color.BLUE)
            }else{
                 layout.setBackgroundColor(Color.GREEN)
            }
        }
    }
}