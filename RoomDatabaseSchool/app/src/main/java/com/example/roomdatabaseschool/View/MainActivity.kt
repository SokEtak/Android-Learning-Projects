package com.example.roomdatabaseschool.View

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabaseschool.Model.Note
import com.example.roomdatabaseschool.NoteApplication
import com.example.roomdatabaseschool.R
import com.example.roomdatabaseschool.ViewModel.NoteViewModel
import com.example.roomdatabaseschool.ViewModel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //In the case that our ViewModel has no parameter
        //noteViewModel = NoteViewModel()

        //in the case out NoteViewModel has 1 parameter(repository) , so we need to use this approach
        val viewModelFactory = NoteViewModelFactory((application as NoteApplication).repository)
        noteViewModel = ViewModelProvider(this,viewModelFactory).get(NoteViewModel::class.java)
        noteViewModel.myAllNotes.observe(this,Observer{ note->
            //update ui

        })
    }
}