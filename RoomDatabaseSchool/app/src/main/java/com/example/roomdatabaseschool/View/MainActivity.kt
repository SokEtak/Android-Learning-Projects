package com.example.roomdatabaseschool.View

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseschool.Adapter.NoteAdapter
import com.example.roomdatabaseschool.NoteApplication
import com.example.roomdatabaseschool.R
import com.example.roomdatabaseschool.ViewModel.NoteViewModel
import com.example.roomdatabaseschool.ViewModel.NoteViewModelFactory
import com.google.android.material.snackbar.Snackbar
import androidx.core.graphics.drawable.toDrawable

class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Register activity result launcher
        addActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
            }
        }

        // Setup RecyclerView
        recyclerView = findViewById(R.id.noteRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        noteAdapter = NoteAdapter(
            mainActivity =   MainActivity(),
            onItemClick = { note ->
                val intent = Intent(this, NoteEditActivity::class.java)
                intent.putExtra("id", note.id)
                intent.putExtra("title", note.title)
                intent.putExtra("description", note.description)
                startActivity(intent)
            },
            onItemLongClick = { note ->
                val builder = androidx.appcompat.app.AlertDialog.Builder(this)
                builder.setTitle("Delete Note")
                builder.setMessage("Are you sure you want to delete this note?")
                builder.setPositiveButton("Delete") { dialog, _ ->
                    noteViewModel.delete(note)
                    Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                builder.setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.show() }
        )

        recyclerView.adapter = noteAdapter

        // Swipe to delete with background
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            private val deleteIcon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_delete24p)!!
            private val background = Color.RED.toDrawable()
            private val intrinsicWidth = deleteIcon.intrinsicWidth
            private val intrinsicHeight = deleteIcon.intrinsicHeight

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val note = noteAdapter.getNoteAt(position)

                noteViewModel.delete(note)

                Snackbar.make(recyclerView, "Note deleted", Snackbar.LENGTH_LONG)
                    .setAction("Undo") {
                        noteViewModel.insert(note)
                    }
                    .show()
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean,
            ) {
                val itemView = viewHolder.itemView
                val backgroundCornerOffset = 20

                if (dX > 0) {
                    background.setBounds(
                        itemView.left, itemView.top,
                        itemView.left + dX.toInt() + backgroundCornerOffset,
                        itemView.bottom
                    )
                } else if (dX < 0) {
                    background.setBounds(
                        itemView.right + dX.toInt() - backgroundCornerOffset,
                        itemView.top, itemView.right, itemView.bottom
                    )
                } else {
                    background.setBounds(0, 0, 0, 0)
                }

                background.draw(c)

                // Draw delete icon
                val itemHeight = itemView.bottom - itemView.top
                val iconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
                val iconBottom = iconTop + intrinsicHeight

                if (dX > 0) {
                    val iconLeft = itemView.left + 40
                    val iconRight = iconLeft + intrinsicWidth
                    deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                } else if (dX < 0) {
                    val iconRight = itemView.right - 40
                    val iconLeft = iconRight - intrinsicWidth
                    deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                }

                deleteIcon.draw(c)

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }).attachToRecyclerView(recyclerView)

        // ViewModel setup
        val viewModelFactory = NoteViewModelFactory((application as NoteApplication).repository)
        noteViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

        noteViewModel.myAllNotes.observe(this, Observer { notes ->
            //update ui
            noteAdapter.setNote(notes)
        })

        // Toolbar menu handling
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.ic_add -> {
                    val intent = Intent(this, NoteAddActivity::class.java)
                    addActivityResultLauncher.launch(intent) // ✅ replaced line
                    true
                }

                R.id.ic_delete -> {
                    showDialogMessage()
                    true
                }

                else -> false
            }
        }
    }

    private fun showDialogMessage(){
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Delete All Notes")
        builder.setMessage("Are you sure you want to delete all notes?")

        builder.setPositiveButton("Yes") { dialog, _ ->
            noteViewModel.deleteAll()
            Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}
