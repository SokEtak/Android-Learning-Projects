package com.example.roomdatabaseschool.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseschool.Model.Note
import com.example.roomdatabaseschool.R
import com.example.roomdatabaseschool.View.MainActivity
import com.example.roomdatabaseschool.ViewModel.NoteViewModel

class NoteAdapter(
    mainActivity: MainActivity,
    private  val onItemClick : (Note) -> Unit,
    private  val onItemLongClick : (Note) -> Unit,
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var notes: List<Note> = ArrayList<Note>()

    class NoteViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.textViewTitle)
        val description : TextView = itemView.findViewById(R.id.textViewDescription)
        val cardView : CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)
        return NoteViewHolder(view)
    }

    //return all number of noteList
    override fun getItemCount() = notes.count()

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title.toString()
        holder.description.text= note.description.toString()

        holder.cardView.setOnClickListener {
            onItemClick(note);
        }

        holder.itemView.setOnLongClickListener {
            onItemLongClick(note)
            true
        }
    }

    //after = is the statement that return
    fun getNoteAt(position: Int) = notes[position]

    fun setNote(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }
}