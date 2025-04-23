package com.example.roomdatabase.Repository

import androidx.lifecycle.LiveData
import com.example.roomdatabase.Model.Note
import com.example.roomdatabase.Room.NoteDao

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()
    suspend fun insert(note: Note) = noteDao.insert(note)
    suspend fun update(note: Note) = noteDao.update(note)
    suspend fun delete(note: Note) = noteDao.delete(note)
    suspend fun deleteAll() = noteDao.deleteAll()
}