package com.example.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    lateinit var replace :Button
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
        replace = findViewById(R.id.replace)
        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        val firstFragment  = FirstFragment()
        fragmentTransaction.add(R.id.frame,firstFragment)
        fragmentTransaction.commit()

        replace.setOnClickListener {
            val fragmentManager2 : FragmentManager = supportFragmentManager
            val fragmentTransaction2 : FragmentTransaction = fragmentManager2.beginTransaction()

            val secondFragment = SecondFragment()
            fragmentTransaction2.addToBackStack(null)// make it usable using back button press like activity
            fragmentTransaction2.replace(R.id.frame,secondFragment)
            fragmentTransaction2.commit()
        }
    }
}