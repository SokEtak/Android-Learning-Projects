package com.example.roomdatabaseschool.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    var title: String ,
    var description:String
){
    @PrimaryKey(autoGenerate = true)
    //must be var
    var id = 0
}