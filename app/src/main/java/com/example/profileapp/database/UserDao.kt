package com.example.profileapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    fun inserUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)
    @Query("SELECT * FROM user_table")
    fun gelAllUser():List<User>

    @Query("UPDATE user_table SET name = :name ,age =:age , jobDescription=:jobDescription WHERE id = :id")
    fun updateCustomUser(name: String, age:Int, jobDescription : String, id :Int)

    @Query("SELECT * FROM User_Table WHERE id= :id")
    fun getCustomUser (id: Int):User
}