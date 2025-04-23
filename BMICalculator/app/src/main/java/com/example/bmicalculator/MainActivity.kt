package com.example.bmicalculator
//sending data from activity to fragment
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    lateinit var edtWeight :EditText
    lateinit var edtHeight :EditText
    lateinit var btn :Button
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
        edtHeight = findViewById(R.id.edtHeight)
        edtWeight = findViewById(R.id.edtWeight)
        btn=findViewById(R.id.button)

        val fragmentManager :FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction= fragmentManager.beginTransaction()
        val bmiFragment = BMIFragment()

        btn.setOnClickListener {
            val weight = edtWeight.toString().toInt()
            val height = edtWeight.toString().toInt()

            val bundle = Bundle()
            //passing data from activity
            bundle.putInt("weight",weight)
            bundle.putInt("height",height)
            bmiFragment.arguments = bundle
            fragmentTransaction.add(R.id.frame,bmiFragment)
            fragmentTransaction.commit()
        }
    }
}