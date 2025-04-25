package com.example.roomdatabase.View

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.roomdatabase.Model.Note
import com.example.roomdatabase.R

class DeleteNoteDialogFragment(
    private val note: Note,
    private val onDeleteConfirmed: (Note) -> Unit
): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Delete Note")
            .setMessage("Are you sure want to delete this '${note.title}'?")
            .setPositiveButton("yes") {_,_->
                onDeleteConfirmed(note)
            }
            .setNegativeButton("No",null)
            .create()
    }
}