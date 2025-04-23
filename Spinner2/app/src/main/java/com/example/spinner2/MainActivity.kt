package com.example.spinner2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.util.rangeTo
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        val spinner : Spinner = findViewById(R.id.spinner)
//        val rootActivity = findViewById<ConstraintLayout>(R.id.main)

        //option 1(easy)
        //val items = listOf("item1","item2",....)

        //option 2(hard) : list is located in: res/values/array
        val items = resources.getStringArray(R.array.dayOfTeWeek)

        val adapter  = ArrayAdapter(this,android.R.layout.simple_spinner_item,items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                if (items[position] != "Select a day"){
                    var dayOfTheWeek  = "weekday"
                    if (position  in  6..7){
                        dayOfTheWeek = "weekend"
                    }
                Toast.makeText(this@MainActivity,"item selected:${items[position]}($dayOfTheWeek)",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
               Toast.makeText(this@MainActivity,"no item selected",Toast.LENGTH_SHORT).show()
            }

        }
    }
}