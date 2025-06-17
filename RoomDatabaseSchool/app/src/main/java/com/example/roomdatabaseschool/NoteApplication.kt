package com.example.roomdatabaseschool

import android.app.Application
import com.example.roomdatabaseschool.Room.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication : Application() {
    //create when needed(for specific screen) , otherwise,it won't create it
    //call when necessary
    val scope = CoroutineScope(SupervisorJob())
    val database by lazy { NoteDatabase.getDatabase(this,scope) }
    val repository by lazy { NoteRepository(database.getNoteDao()) }
}