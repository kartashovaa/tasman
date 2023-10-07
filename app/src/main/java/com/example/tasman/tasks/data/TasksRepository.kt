package com.example.tasman.tasks.data

import com.example.tasman.tasks.data.room.TasksDao
import com.example.tasman.tasks.model.Task
import com.example.tasman.tasks.model.TaskForm
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TasksRepository @Inject constructor(
    private val dao: TasksDao
) {

    fun loadAll(): Flow<List<Task>> = dao.getAll()

    suspend fun complete(taskId: Long) = dao.complete(taskId)

    suspend fun create(task: TaskForm) = dao.insert(
        Task(
            title = task.title,
            description = task.description.takeIf(String::isNotBlank),
        )
    )
}