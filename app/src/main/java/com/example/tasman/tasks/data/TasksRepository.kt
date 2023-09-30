package com.example.tasman.tasks.data

import com.example.tasman.tasks.data.room.TasksDao
import com.example.tasman.tasks.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TasksRepository @Inject constructor(
    private val dao: TasksDao
) {

    fun loadAll(): Flow<List<Task>> = dao.getAll()

    suspend fun complete(taskId: Long) = dao.complete(taskId)
}