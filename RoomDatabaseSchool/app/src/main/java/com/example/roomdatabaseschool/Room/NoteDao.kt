package com.example.roomdatabaseschool.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomdatabaseschool.Model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    //using suspend to make it not run on Main Thread
    //prevent from stucking the app
    //kotlin coroutine


    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Delete from note_table")
    suspend fun deleteAllNotes()

    //No need to use suspend keyword
    @Query("SELECT * FROM note_table ORDER BY id ASC")
    //to make it observe from database data(make it update automatically when we update,insert,...),should use it with Flow
    //Flow is return as live data
    fun getAllNotes(): Flow<List<Note>>
}