package com.example.tasman.tasks.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tasman.tasks.model.Task
import com.example.tasman.tasks.ui.preview.TaskPreview
import com.example.tasman.tasks.ui.preview.TaskProvider

@Composable
fun TasksListScreen(
    viewModel: TasksListViewModel = hiltViewModel(),
    onNewTaskClicked: () -> Unit = {},
) {
    val tasksState by viewModel.tasks.collectAsState()
    TasksListScreenBody(tasksState, viewModel::completeTask, onNewTaskClicked)
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalFoundationApi::class)
private fun TasksListScreenBody(
    @PreviewParameter(TasksListUiStateProvider::class)
    state: TasksListUiState,
    onTaskCompleted: (Long) -> Unit = {},
    onNewTaskClicked: () -> Unit = {},
) = Box {
    when (state) {
        TasksListUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        TasksListUiState.Empty -> Text(text = "There is nothing to do", modifier = Modifier.align(Alignment.Center))
        is TasksListUiState.Content -> LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(state.tasks, key = { _, task -> task.id }) { index, task ->
                TaskPreview(task, modifier = Modifier.animateItemPlacement()) { id, isCompleted ->
                    if (isCompleted) onTaskCompleted(id)
                }
                if (index < state.tasks.lastIndex) Divider(modifier = Modifier.animateItemPlacement())
            }
        }
    }

    if (state !is TasksListUiState.Loading) {
        FloatingActionButton(
            onClick = onNewTaskClicked,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Image(imageVector = Icons.Filled.Add, contentDescription = null)
        }
    }
}


private class TasksListUiStateProvider(
    private val tasksProvider: PreviewParameterProvider<Task> = TaskProvider()
) : PreviewParameterProvider<TasksListUiState> {
    override val values: Sequence<TasksListUiState>
        get() = sequenceOf(
            TasksListUiState.Loading,
            TasksListUiState.Empty,
            TasksListUiState.Content(tasksProvider.values.toList())
        )
}
