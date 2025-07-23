package com.example.scheduleapp.repositories

import AchievesTodo

import com.example.scheduleapp.rooms.dao.AchievesTodoDao
import kotlinx.coroutines.flow.Flow

class AchievesTodoRepository(achievesTodoDao: AchievesTodoDao) {

    val allAchievesTodo : Flow<List<AchievesTodo>> =  achievesTodoDao.getAllAchievesTodos()

}