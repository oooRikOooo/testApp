package com.example.testapp.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface  DatabaseDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: Entity)

    @Query("SELECT * FROM user_table ORDER BY userId ASC")
    fun readAllData(): LiveData<List<Entity>>

    @Query("SELECT * FROM user_table WHERE email =:email")
    suspend fun getEmail(email: String): Entity?

    @Query("SELECT * FROM user_table WHERE password =:password")
    suspend fun getPassword(password: String): Entity?

    @Query("SELECT * FROM user_table WHERE email LIKE :email")
    fun getAccount(email: String): Entity

    @Query("UPDATE user_table SET isLogged=:isLogged WHERE email = :email")
    fun update(isLogged: Boolean, email: String)

    @Query("SELECT * FROM user_table WHERE  isLogged Like :isLogged")
    fun getActiveAccount(isLogged: Boolean): Entity

    @Query("UPDATE user_table SET isLogged=:isLogged")
    fun updateAll(isLogged: Boolean)

}