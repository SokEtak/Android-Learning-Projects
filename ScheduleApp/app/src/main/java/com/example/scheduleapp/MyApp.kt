package com.example.scheduleapp

import android.app.Application
import com.example.scheduleapp.repositories.TodosRepository
import com.example.scheduleapp.rooms.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApp : Application() {
    val scope = CoroutineScope(SupervisorJob())
    val database by lazy { AppDatabase.getDatabase(this, scope) }
    val todosRepository by lazy { TodosRepository(database.todosDao()) }
}
