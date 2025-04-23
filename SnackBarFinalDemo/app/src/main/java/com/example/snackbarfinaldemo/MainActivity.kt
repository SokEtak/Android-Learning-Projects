package com.example.snackbarfinaldemo

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tv = findViewById<TextView>(R.id.textView)
        val b1  = findViewById<Button>(R.id.button)
        val b2  = findViewById<Button>(R.id.button2)
        val b3  = findViewById<Button>(R.id.button3)
        val mainLayout = findViewById<ConstraintLayout>(R.id.main)

        b1.setOnClickListener {
            Snackbar.make(mainLayout,"Default SnackBar Demo",Snackbar.LENGTH_SHORT).show()
        }
        b2.setOnClickListener {
            Snackbar.make(mainLayout,"Action snackBar demo",Snackbar.LENGTH_SHORT).
            setAction("UNDO"){//action
                Toast.makeText(this,"Item Restore",Toast.LENGTH_LONG).show()
            }.show()
        }
        b3.setOnClickListener {
             val snackBar : Snackbar = Snackbar.make(mainLayout,"Zip Zip Zap Zap Zap",Snackbar.LENGTH_SHORT)
            snackBar.setTextColor(Color.WHITE)
            snackBar.setBackgroundTint(Color.BLUE)
            snackBar.setAction("Zip Zip"){
                snackBar.dismiss()
                Toast.makeText(this,"We are in group together",Toast.LENGTH_SHORT).show()}
            snackBar.show()
        }

    }
}