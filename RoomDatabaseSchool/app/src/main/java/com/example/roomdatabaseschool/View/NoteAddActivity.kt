package com.example.roomdatabaseschool.View

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabaseschool.Model.Note
import com.example.roomdatabaseschool.Repository.NoteRepository
import com.example.roomdatabaseschool.Room.NoteDatabase
import com.example.roomdatabaseschool.ViewModel.NoteViewModel
import com.example.roomdatabaseschool.ViewModel.NoteViewModelFactory
import androidx.lifecycle.lifecycleScope // 👈 Required import
import com.example.roomdatabaseschool.R

class NoteAddActivity : AppCompatActivity() {

    private lateinit var titleEt: TextView
    private lateinit var descriptionEt: TextView
    private lateinit var saveBtn: Button

    // make it **lateinit var** but _initialize_ it in onCreate
    private lateinit var noteViewModel: NoteViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_note_add)

        // ── view binding (simplified) ────────────────────────────────
        titleEt       = findViewById(R.id.edtTittle)
        descriptionEt = findViewById(R.id.edtDescription)
        saveBtn       = findViewById(R.id.btnAddSave)

        // ── initialise ViewModel safely ──────────────────────────────
        val dao = NoteDatabase.getDatabase(application, lifecycleScope).getNoteDao()
        val repository = NoteRepository(dao)
        val factory    = NoteViewModelFactory(repository)
        noteViewModel  = ViewModelProvider(this, factory)[NoteViewModel::class.java]

        // ── click listener ──────────────────────────────────────────
        saveBtn.setOnClickListener {
            val newTitle = titleEt.text.toString().trim()
            val newDesc  = descriptionEt.text.toString().trim()

            if (newTitle.isBlank() && newDesc.isBlank()) {
                Toast.makeText(this, "Nothing to save", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            noteViewModel.insert(Note(newTitle, newDesc))
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()

            // return to list screen
            startActivity(Intent(this, MainActivity::class.java))
            finish()               // optional: kill this screen
        }
    }
}
