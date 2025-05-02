package com.example.roomdatabase.View

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper // ✅ Added
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.Model.Note
import com.example.roomdatabase.R
import com.example.roomdatabase.ViewModel.NoteViewModel
import com.example.roomdatabase.NoteAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteAdapter
    private val noteViewModel: NoteViewModel by viewModels()

    private var isMenuOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup RecyclerView
        recyclerView = findViewById(R.id.noteRecyclerView)

        adapter = NoteAdapter(
            emptyList(),
            onItemClick = { note ->
                Toast.makeText(this, "Selected: ${note.title}", Toast.LENGTH_SHORT).show()
            },
            onItemLongClick = { note ->
                val dialog = DeleteNoteDialogFragment(note) { confirmedNote ->
                    noteViewModel.delete(confirmedNote)
                    Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show()
                }
                dialog.show(supportFragmentManager, "DeleteNoteDialog")
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // ✅ Add swipe left to open update dialog
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val note = adapter.getNoteAt(position)

                val dialog = UpdateNoteDialogFragment(note) { updatedNote ->
                    noteViewModel.update(updatedNote)
                    Toast.makeText(this@MainActivity, "Note updated", Toast.LENGTH_SHORT).show()
                }

                dialog.show(supportFragmentManager, "UpdateNoteDialog")

                // Prevent item from disappearing after swipe
                adapter.notifyItemChanged(position)
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // Observe notes from ViewModel
        noteViewModel.allNotes.observe(this, Observer { notes ->
            adapter.setNotes(notes)
        })

        // Setup Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        toolbar.setNavigationOnClickListener {
            isMenuOpen = !isMenuOpen
            val iconRes = if (isMenuOpen) R.drawable.ic_menu_open24 else R.drawable.ic_menu24
            toolbar.setNavigationIcon(iconRes)
        }

        // Toolbar menu buttons
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.ic_add -> {
                    val dialog = AddNoteDialogFragment { title, desc ->
                        val note = Note(title = title, description = desc)
                        noteViewModel.insert(note)
                        Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
                    }
                    dialog.show(supportFragmentManager, "AddNoteDialog")
                    true
                }

                R.id.ic_delete -> {
                    noteViewModel.deleteAll()
                    Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
    }
}
