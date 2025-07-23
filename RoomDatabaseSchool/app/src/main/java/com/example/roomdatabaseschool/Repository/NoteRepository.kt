package com.example.roomdatabaseschool.Repository

import androidx.annotation.WorkerThread
import com.example.roomdatabaseschool.Model.Note
import com.example.roomdatabaseschool.Room.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(var noteDao: NoteDao) {

     var myAllNotes : Flow<List<Note>> = noteDao.getAllNotes()

    //Make it work in Single Thread
    @WorkerThread
    suspend fun insert(note: Note){
        noteDao.insert(note)
     }
    @WorkerThread
    suspend fun update(note: Note){
        noteDao.update(note)
     }
    @WorkerThread
    suspend fun delete(note: Note){
        noteDao.delete(note)
     }
    @WorkerThread
    suspend fun deleteAllNotes(){
        noteDao.deleteAllNotes()
     }
}