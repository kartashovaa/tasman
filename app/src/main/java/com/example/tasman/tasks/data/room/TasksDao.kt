package com.example.tasman.tasks.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tasman.tasks.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM tasks WHERE isCompleted=0")
    fun getAll(): Flow<List<Task>>

    @Insert
    suspend fun insert(task: Task): Long

    @Query("UPDATE tasks SET isCompleted=1 WHERE id=:taskId")
    suspend fun complete(taskId: Long)
}