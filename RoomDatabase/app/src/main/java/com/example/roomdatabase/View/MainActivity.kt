package com.example.roomdatabase.View

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.Model.Note
import com.example.roomdatabase.R
import com.example.roomdatabase.ViewModel.NoteViewModel
import com.example.roomdatabase.ui.NoteAdapter

class MainActivity : ComponentActivity() {
    private val noteViewModel: NoteViewModel by viewModels()
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titleField = findViewById<EditText>(R.id.editTextTitle)
        val contentField = findViewById<EditText>(R.id.editTextContent)
        val button = findViewById<Button>(R.id.buttonSave)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = NoteAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        noteViewModel.allNotes.observe(this, Observer { notes ->
            adapter.setNotes(notes)
        })

        button.setOnClickListener {
            val title = titleField.text.toString()
            val description = contentField.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                noteViewModel.insert(Note(title = title, description = description))
                titleField.text.clear()
                contentField.text.clear()
            }
        }
    }
}
