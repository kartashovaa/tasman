package com.example.tasman.tasks.ui.list

import com.example.tasman.tasks.model.Task

sealed class TasksListUiState {
    object Loading : TasksListUiState()
    object Empty : TasksListUiState()
    data class Content(val tasks: List<Task>) : TasksListUiState()

    companion object {
        fun create(tasks: List<Task>) = if(tasks.isEmpty()) Empty else Content(tasks)
    }
}