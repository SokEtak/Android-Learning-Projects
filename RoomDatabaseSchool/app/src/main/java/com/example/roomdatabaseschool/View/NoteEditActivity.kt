package com.example.roomdatabaseschool.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.roomdatabaseschool.Model.Note
import com.example.roomdatabaseschool.R
import com.example.roomdatabaseschool.Repository.NoteRepository
import com.example.roomdatabaseschool.Room.NoteDatabase
import com.example.roomdatabaseschool.ViewModel.NoteViewModel
import com.example.roomdatabaseschool.ViewModel.NoteViewModelFactory

class NoteEditActivity : AppCompatActivity() {
    private lateinit var titleEt: EditText
    private lateinit var descriptionEt: EditText
    private lateinit var updateBtn: Button
    private lateinit var cancelBtn: Button
    private lateinit var noteViewModel: NoteViewModel

    private var noteId: Int = -1  // default invalid ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_note_edit)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        titleEt = findViewById(R.id.edtTittle)
        descriptionEt = findViewById(R.id.edtDescription)
        updateBtn = findViewById(R.id.btnUpdate)
        cancelBtn = findViewById(R.id.btnCancelUpdate)

        // Initialize ViewModel
        val dao = NoteDatabase.getDatabase(application, lifecycleScope).getNoteDao()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]

        // Get data from intent
        noteId = intent.getIntExtra("id", -1)
        val oldTitle = intent.getStringExtra("title")
        val oldDesc = intent.getStringExtra("description")

        titleEt.setText(oldTitle)
        descriptionEt.setText(oldDesc)

        updateBtn.setOnClickListener {
            val newTitle = titleEt.text.toString().trim()
            val newDesc = descriptionEt.text.toString().trim()

            if (newTitle.isBlank() && newDesc.isBlank()) {
                Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (noteId != -1) {
                val updatedNote = Note(newTitle, newDesc)
                updatedNote.id = noteId  // Set ID for updating

                noteViewModel.update(updatedNote)
                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Error updating note", Toast.LENGTH_SHORT).show()
            }
        }

        cancelBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
