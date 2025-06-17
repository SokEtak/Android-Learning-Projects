package com.example.scheduleapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todo_table")

data class Todos(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var tittle : String?,
    var description : String?,
    var startDate : Date,
    var dueDate : Date,
    var isComplete : Boolean,
    var place : String?,
    var completedDate : Date?
)