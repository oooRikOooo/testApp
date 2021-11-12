package com.example.testapp.DataBase

import androidx.lifecycle.LiveData

class Repository(private val dao: DatabaseDao) {
    val readAllData:LiveData<List<Entity>> = dao.readAllData()

    suspend fun addUser(user: Entity){
        dao.addUser(user)
    }

    suspend fun getEmail(email: String):Entity?{
        return dao.getEmail(email)
    }

    suspend fun getPassword(password: String):Entity?{
        return dao.getPassword(password)
    }

     suspend fun isValidAccount(email: String,password: String): Boolean {
        val accountEmail = dao.getAccount(email)
         return if(accountEmail!=null){
             (accountEmail.password == password)
         } else
             false

    }

    fun getActiveAccount(isLogged: Boolean):Entity?{
        return dao.getActiveAccount(isLogged)
    }

    suspend fun isLoggedAcoount(isLogged: Boolean, email: String){
        dao.update(isLogged,email)
    }

    fun updateAll(isLogged: Boolean){
        dao.updateAll(isLogged)
    }

    /*suspend fun getUserName(userName: String): Entity?{
        return dao.getUsername(userName)
    }*/
}