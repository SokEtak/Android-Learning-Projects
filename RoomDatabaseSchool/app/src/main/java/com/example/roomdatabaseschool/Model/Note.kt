package com.example.roomdatabaseschool.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(title: String , description:String) {
    @PrimaryKey(autoGenerate = true) val id = 0
}