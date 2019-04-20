package com.matej.trecazadaca.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.matej.trecazadaca.model.Task

@Dao
interface TaskDao {
    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM tasks")
    fun selectTitle(): MutableList<Task>

    @Query("DELETE FROM tasks")
    fun deleteAll()
}