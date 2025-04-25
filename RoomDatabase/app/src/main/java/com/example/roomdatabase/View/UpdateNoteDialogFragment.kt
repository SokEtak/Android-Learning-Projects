package com.example.roomdatabase.View

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.roomdatabase.Model.Note
import com.example.roomdatabase.R

class UpdateNoteDialogFragment(
    private val note: Note,
    private val onUpdateConfirmed: (Note) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_add_note_dialog, null)
        val titleEditText = view.findViewById<EditText>(R.id.editTitle)
        val descriptionEditText = view.findViewById<EditText>(R.id.editDescription)

        // Pre-fill with existing note data
        titleEditText.setText(note.title)
        descriptionEditText.setText(note.description)

        return AlertDialog.Builder(requireContext())
            .setTitle("Update Note")
            .setView(view)
            .setPositiveButton("Update") { _, _ ->
                val updatedTitle = titleEditText.text.toString()
                val updatedDescription = descriptionEditText.text.toString()

                if (updatedTitle.isNotEmpty() && updatedDescription.isNotEmpty()) {
                    val updatedNote = note.copy(title = updatedTitle, description = updatedDescription)
                    onUpdateConfirmed(updatedNote)
                } else {
                    Toast.makeText(requireContext(), "Fields can't be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}
