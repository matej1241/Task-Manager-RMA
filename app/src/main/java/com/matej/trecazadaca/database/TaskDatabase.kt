package com.matej.trecazadaca.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.matej.trecazadaca.MyApplication
import com.matej.trecazadaca.model.Task

@Database(version = 1, entities = arrayOf(Task::class), exportSchema = false)
abstract class TaskDatabase : RoomDatabase(){

    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(): TaskDatabase{
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(MyApplication.ApplicationContext, TaskDatabase::class.java, "tasks")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as TaskDatabase
        }
    }
}
