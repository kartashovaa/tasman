package com.example.tasman.tasks.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasman.tasks.data.TasksRepository
import com.example.tasman.tasks.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    repository: TasksRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val task: StateFlow<Task?> = repository.load(requireNotNull(savedStateHandle["taskId"]))
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
}