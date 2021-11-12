package com.example.testapp.DataBase

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application):AndroidViewModel(application) {

    private val readAllData: LiveData<List<Entity>>
    //private val aa = MutableLiveData<Boolean>()
    //private val result = MutableLiveData<Boolean>()
    private val repository:Repository

    init {
        val userDao = DataBase.getDataBase(application).userDao()
        repository = Repository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user : Entity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    fun checkUser(user: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.getEmail(user)
        }
    }

    fun getActiveAccount(isLogged: Boolean):LiveData<Entity>{
        val result1 = MutableLiveData<Entity>()
        viewModelScope.launch (Dispatchers.IO){
            val a = repository.getActiveAccount(isLogged)
            result1.postValue(a!!)
        }
        return result1
    }



    fun checkIsValidAccount(username: String, password: String):LiveData<Boolean>{
        //var a = false
        val result = MutableLiveData<Boolean>()
        viewModelScope.launch(Dispatchers.IO){
            //repository.count(user.email,user.password)
            val a = repository.isValidAccount(username,password)
            result.postValue(a)
        }
        return result
    }

    fun update(isLogged: Boolean, email: String){

        viewModelScope.launch(Dispatchers.IO){
            repository.isLoggedAcoount(isLogged,email)
        }
    }

    fun updateAll(isLogged:Boolean){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateAll(isLogged)
        }
    }
    /*fun checkIsValidAccount(username: String, password: String){
        viewModelScope.launch(Dispatchers.IO){
            result.value = repository.isValidAccount(username,password)
        }
    }*/

}