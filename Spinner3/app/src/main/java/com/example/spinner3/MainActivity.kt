package com.example.spinner3

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() , AdapterView.OnItemSelectedListener{
   private lateinit var tv : TextView
   lateinit private var spn : Spinner
   private lateinit var btn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tv = findViewById(R.id.textView)
        spn = findViewById(R.id.spinner)
        btn = findViewById(R.id.button)

        //val item = resources.getStringArray(R.array.countries)

        val arrayAdapter = ArrayAdapter.createFromResource(
            this, R.array.countries,  // This is your array resource
            android.R.layout.simple_spinner_item  // Layout for each item in the spinner
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)  // Layout for the dropdown view
        }

        spn.adapter = arrayAdapter  // Set the adapter for your spinner


        spn.adapter = arrayAdapter

        btn.setOnClickListener {
            println("Button clicked")
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
         btn.text= parent!!.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        println("No item select")
    }
}