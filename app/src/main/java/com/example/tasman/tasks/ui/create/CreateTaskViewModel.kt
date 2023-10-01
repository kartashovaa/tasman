package com.example.tasman.tasks.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasman.tasks.data.TasksRepository
import com.example.tasman.tasks.model.TaskForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val repository: TasksRepository
) : ViewModel(), TaskEditDelegate {

    private val _isFormCompleted = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val isFormCompleted: Flow<Unit> = _isFormCompleted.asSharedFlow()

    private val _form = MutableStateFlow(TaskForm())
    val form: StateFlow<TaskForm> = _form.asStateFlow()

    override fun updateTitle(title: String) = updateForm { copy(title = title) }
    override fun updateDescription(description: String) = updateForm { copy(description = description) }

    override fun complete() {
        val form = form.value
        viewModelScope.launch {
            check(form.isValid)
            repository.create(form)
            _isFormCompleted.tryEmit(Unit)
        }
    }

    private fun updateForm(update: TaskForm.() -> TaskForm) = _form.update(update)
}