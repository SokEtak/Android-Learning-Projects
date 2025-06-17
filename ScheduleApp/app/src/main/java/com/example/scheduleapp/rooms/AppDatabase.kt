package com.example.scheduleapp.rooms

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.scheduleapp.models.Todos
import com.example.scheduleapp.models.AchievedTodos
import com.example.scheduleapp.rooms.dao.TodosDao
import com.example.scheduleapp.rooms.dao.AchievedTodosDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.scheduleapp.Converters

@Database(entities = [Todos::class, AchievedTodos::class], version = 1)
@TypeConverters(Converters::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun todosDao(): TodosDao
    abstract fun achievedTodosDao(): AchievedTodosDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                val todosDao = database.todosDao()
                val achievedDao = database.achievedTodosDao()

                scope.launch {
                    // Example pre-populate Todos
                    todosDao.insert(Todos(
                        tittle = "Sample Todo",
                        description = "This is a sample todo",
                        startDate = java.util.Date(),
                        dueDate = java.util.Date(),
                        isComplete = false,
                        place = "Home",
                        completedDate = java.util.Date()
                    ))

                    // Example pre-populate AchievedTodos
                    achievedDao.insert(AchievedTodos(
                        tittle = "Completed Todo",
                        description = "This is an achieved todo",
                        startDate = java.util.Date(),
                        finishDate = java.util.Date(),
                        isComplete = true,
                        place = "Office",
                        completedDate = java.util.Date()
                    ))
                }
            }
        }
    }
}
