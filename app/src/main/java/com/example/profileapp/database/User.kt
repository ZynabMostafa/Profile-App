package com.example.profileapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_Table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id :Int = 0,
    var name : String,
    var age :Int,
    var jobDescription :String
)
