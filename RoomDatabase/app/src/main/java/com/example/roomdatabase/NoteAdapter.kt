package com.example.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.Model.Note

class NoteAdapter(
    private var notes: List<Note>,
    private val onItemClick: (Note) -> Unit, // Listener for item click
    private val onItemLongClick: (Note) -> Unit // <- Add this
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textTitle)
        val description: TextView = itemView.findViewById(R.id.textDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title
        holder.description.text = note.description

        // Set click listener for each item
        holder.itemView.setOnClickListener {
            onItemClick(note)
        }

        holder.itemView.setOnLongClickListener {
            onItemLongClick(note)
            true
        }
    }

    fun getNoteAt(position: Int): Note {
        return notes[position]
    }


    fun setNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}

