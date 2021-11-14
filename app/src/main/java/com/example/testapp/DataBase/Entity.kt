package com.example.testapp.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    var email:String,

    var password:String,

    var isLogged:Boolean
)