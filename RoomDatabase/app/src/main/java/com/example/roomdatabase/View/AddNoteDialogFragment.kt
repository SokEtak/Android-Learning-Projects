package com.example.roomdatabase.View

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.roomdatabase.R

class AddNoteDialogFragment(private val onSave: (String, String) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_add_note_dialog, null)

        val titleInput = view.findViewById<EditText>(R.id.editTitle)
        val descInput = view.findViewById<EditText>(R.id.editDescription)

        return AlertDialog.Builder(requireContext())
            .setTitle("Add New Note")
            .setView(view)
            .setPositiveButton("Save") { _, _ ->
                val title = titleInput.text.toString().trim()
                val description = descInput.text.toString().trim()
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    onSave(title, description)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}
