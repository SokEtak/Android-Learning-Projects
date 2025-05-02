package com.example.roomdatabaseschool.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert
    fun insert()
    @Update
    suspend fun update()
    @Delete
    suspend fun delete()
    @Query("Delete from note_table")
    fun deleteAll()
}