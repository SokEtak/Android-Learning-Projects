package com.example.gridview

import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), CustomAdapter.OnItemClickListener {

    private lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enabling edge-to-edge display
        setContentView(R.layout.activity_main)

        // Apply window insets to avoid overlapping content with system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the GridView
        gridView = findViewById(R.id.gridView)

        // Create a list of items (countries with images)
        val items = arrayListOf(
            Item(R.drawable.laos, "Laos"),
            Item(R.drawable.cambodia, "Cambodia"),
            Item(R.drawable.malasia, "Malaysia"),
            Item(R.drawable.usa, "USA"),
            Item(R.drawable.vietnam, "Vietnam"),
            Item(R.drawable.thai, "Thailand"),
            Item(R.drawable.singapore, "Singapore"),
            Item(R.drawable.england, "England"),
            Item(R.drawable.germany, "Germany")
        )

        // Create and set the adapter for the GridView
        val adapter = CustomAdapter(this, items)
        gridView.adapter = adapter
        // Set the listener for item clicks
        adapter.setOnItemClickListener(this)
    }

    // Implement the OnItemClickListener interface
    override fun onItemClick(position: Int) {
        val clickedItem = (gridView.adapter as CustomAdapter).getItem(position) as Item
        // Show a Toast or perform any action with the clicked item
        Toast.makeText(this, "Clicked: ${clickedItem.name}", Toast.LENGTH_SHORT).show()
    }
}
