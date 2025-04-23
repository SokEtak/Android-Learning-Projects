package com.example.lisview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() {
    private lateinit var myListVIew : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        myListVIew = findViewById(R.id.lv)
        val countries = resources.getStringArray(R.array.countiries_array)
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,countries)
        myListVIew.adapter = adapter

        myListVIew.setOnItemClickListener { parent, _, position, _ ->
            val country = parent.getItemAtPosition(position)
            Toast.makeText(this,
                "List Selected:$country",
                Toast.LENGTH_SHORT).show()
        }

    }
}