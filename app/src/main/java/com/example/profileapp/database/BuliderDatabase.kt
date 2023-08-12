package com.example.profileapp.database

import android.content.Context
import androidx.room.Room

class BuliderDatabase (context: Context) {

   private val data by lazy {
        Room.databaseBuilder(context = context , DataBase::class.java , "data")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
    }

    fun getDao():UserDao{
       return data.userDao()
    }
}