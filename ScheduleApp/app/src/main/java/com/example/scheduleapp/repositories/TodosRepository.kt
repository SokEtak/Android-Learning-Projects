package com.example.scheduleapp.repositories


import com.example.scheduleapp.models.Todos
import com.example.scheduleapp.rooms.dao.TodosDao
import kotlinx.coroutines.flow.Flow


class TodosRepository(private val todosDao: TodosDao) {

    val allTodos: Flow<List<Todos>> = todosDao.getAllTodos()

    suspend fun insert(todo: Todos) {
        todosDao.insert(todo)
    }

    suspend fun update(todo: Todos) {
        todosDao.update(todo)
    }

    suspend fun delete(todo: Todos) {
        todosDao.delete(todo)
    }

    suspend fun deleteAll() {
        todosDao.deleteAllNotes()
    }
}