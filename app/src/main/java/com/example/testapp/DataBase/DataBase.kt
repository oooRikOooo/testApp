package com.example.testapp.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Entity::class], version = 2, exportSchema = true)
abstract class DataBase : RoomDatabase() {

    abstract fun userDao(): DatabaseDao

    companion object {
        @Volatile
        private var INSTANCE : DataBase?=null

        fun getDataBase(context: Context): DataBase {

            val tempInstance =  INSTANCE

            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext, DataBase::class.java, "user_database"
                ).build()

                INSTANCE = instance

                return instance
            }
        }
    }
}