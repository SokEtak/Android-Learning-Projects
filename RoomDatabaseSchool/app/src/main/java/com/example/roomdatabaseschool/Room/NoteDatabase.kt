package com.example.roomdatabaseschool.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomdatabaseschool.Model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//In the case we have more than 1 table
//@Database(entities = [Note::class,Product::class,Employee:class,...], version = 1)

@Database(entities = [Note::class], version = 1)//when modify this database must increase the version
//in one time we cannot create multiple instance
abstract class NoteDatabase:RoomDatabase() {
    abstract fun getNoteDao() : NoteDao
    //singleton
    companion object {
        //
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context,scope: CoroutineScope): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, //prevent from memory leak
                    NoteDatabase::class.java,
                    "note_database"
                    ).addCallback(NoteDatabaseCallBack(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    //this is similar to seeder in web
    class NoteDatabaseCallBack(val scope:CoroutineScope) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                //cannot use it because insertOneWas suspend function
                //database.getNoteDao().insert(Note("Title1","Description1"))
                val noteDao = database.getNoteDao()
                scope.launch{
                    noteDao.insert(Note("Title1","Description1"))
                    noteDao.insert(Note("Title2","Description2"))
                    noteDao.insert(Note("Title3","Description3"))
                    noteDao.insert(Note("Title4","Description4"))
                    noteDao.insert(Note("Title5","Description5"))
                    noteDao.insert(Note("Title6","Description6"))
                    noteDao.insert(Note("Title7","Description7"))
                    noteDao.insert(Note("Title8","Description8"))
                    noteDao.insert(Note("Title9","Description9"))
                    noteDao.insert(Note("Title10","Description10"))
                    noteDao.insert(Note("Title11","Description11"))
                    noteDao.insert(Note("Title12","Description12"))
                }
            }
        }
    }
}
