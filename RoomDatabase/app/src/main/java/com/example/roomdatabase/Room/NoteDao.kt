package com.example.roomdatabase.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomdatabase.Model.Note

@Dao
interface NoteDao {
    //suspend = asynchronous keyword
    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<Note>>
    @Insert
    suspend fun insert(note:Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Delete From note_table")
    suspend fun deleteAll()
}