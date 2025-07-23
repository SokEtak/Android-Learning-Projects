package com.example.scheduleapp.rooms.dao

import AchievesTodo
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AchievesTodoDao {

    @Query("SELECT * FROM achieved_todos_view")
    fun getAllAchievesTodos() : Flow<List<AchievesTodo>>
}