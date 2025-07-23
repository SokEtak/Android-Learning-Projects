package com.example.scheduleapp.rooms.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.scheduleapp.models.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodosDao {
    @Insert
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("Delete from todo_table")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM todo_table WHERE id = :id")
    fun getTodoById(id: Int): LiveData<Todo>

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTodos() : Flow<List<Todo>>
}