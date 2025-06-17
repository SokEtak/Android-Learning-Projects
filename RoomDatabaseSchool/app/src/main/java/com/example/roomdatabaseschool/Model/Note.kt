package com.example.roomdatabaseschool.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    val title: String ,
    val description:String
){
    @PrimaryKey(autoGenerate = true)
    //must be var
    var id = 0
}