package com.example.scheduleapp.rooms

import AchievesTodo
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.scheduleapp.models.Todo
import com.example.scheduleapp.rooms.dao.TodosDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.scheduleapp.Converters
import com.example.scheduleapp.rooms.dao.AchievesTodoDao
import java.util.Date

@Database(
    entities = [Todo::class],
    views = [AchievesTodo::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun todosDao(): TodosDao
    abstract fun achievesTodoDao(): AchievesTodoDao

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
                    .fallbackToDestructiveMigration(false) // 👈 add this
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
                val achievesTodo = database.achievesTodoDao()
                scope.launch {
                    // Example pre-populate Todos
                    todosDao.insert(Todo(
                        tittle = "Sample Todo",
                        description = "This is a sample todo",
                        startDate = Date(),
                        dueDate = Date(),
                        isComplete = false,
                        place = "Home",
                        completedDate = Date()
                    ))

                }
            }
        }
    }
}
