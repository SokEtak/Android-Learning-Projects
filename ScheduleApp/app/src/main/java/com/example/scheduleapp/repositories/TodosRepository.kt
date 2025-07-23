package com.example.scheduleapp.repositories


import androidx.lifecycle.LiveData
import com.example.scheduleapp.models.Todo
import com.example.scheduleapp.rooms.dao.TodosDao
import kotlinx.coroutines.flow.Flow


class TodosRepository(private val todosDao: TodosDao) {

    val allTodos: Flow<List<Todo>> = todosDao.getAllTodos()

    suspend fun insert(todo: Todo) {
        todosDao.insert(todo)
    }

    suspend fun update(todo: Todo) {
        todosDao.update(todo)
    }

    suspend fun delete(todo: Todo) {
        todosDao.delete(todo)
    }

    suspend fun deleteAll() {
        todosDao.deleteAllNotes()
    }

    fun getTodoById(id: Int): LiveData<Todo> {
        return todosDao.getTodoById(id)
    }
}