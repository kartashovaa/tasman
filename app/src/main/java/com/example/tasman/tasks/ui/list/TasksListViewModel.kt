package com.example.tasman.tasks.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasman.tasks.data.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(
    private val repository: TasksRepository
) : ViewModel() {

    val tasks: StateFlow<TasksListUiState> = repository.loadAll()
        .map(TasksListUiState.Companion::create)
        .stateIn(viewModelScope, WhileSubscribed(5000), TasksListUiState.Loading)

    fun completeTask(taskId: Long) {
        viewModelScope.launch {
            repository.complete(taskId)
        }
    }
}