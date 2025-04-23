package com.example.switchbutton

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")

    lateinit var switchBtn : Switch
    lateinit var tv : TextView

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
        switchBtn =  findViewById(R.id.switch2)
        tv = findViewById<TextView>(R.id.textView)

        switchBtn.setOnClickListener {
            if(switchBtn.isChecked){
                tv.text = "it's possible"
                tv.setTextColor(Color.GREEN)
            }else{
                tv.text = "it's impossible"
                tv.setTextColor(Color.RED)

            }
        }
    }
}