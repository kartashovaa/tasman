package com.example.tasman.tasks.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tasman.tasks.model.Task
import com.example.tasman.tasks.ui.preview.TaskProvider

@JvmInline
value class TaskDetailsDestination(
    private val controller: NavController
) {
    fun navigate(taskId: Long) = controller.navigate("details/$taskId")
    fun register(builder: NavGraphBuilder) = with(builder) {
        composable(
            route = "details/{taskId}",
            arguments = listOf(navArgument("taskId") { type = androidx.navigation.NavType.LongType })
        ) {
            TaskDetailsScreen(onBackClicked = controller::popBackStack)
        }
    }
}

@Composable
fun TaskDetailsScreen(
    viewModel: TaskDetailsViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {
    val task by viewModel.task.collectAsState()
    task?.let {
        TaskDetailsScreenBody(it, onBackClicked)
    }
}

//TODO:
// 1) add placeholders
// 2) save changes
@Composable
@Preview(showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
private fun TaskDetailsScreenBody(
    @PreviewParameter(TaskProvider::class)
    task: Task,
    onBackClicked: () -> Unit = {}
) = Column(
    modifier = Modifier
        .padding(horizontal = 16.dp)
        .scrollable(rememberScrollState(), Orientation.Vertical)
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier.clickable(onClick = onBackClicked)
            )
        }
    )

    BasicTextField(
        value = task.title,
        onValueChange = { /* TODO */ },
        textStyle = MaterialTheme.typography.bodyLarge
    )

    BasicTextField(
        value = task.description.orEmpty(),
        onValueChange = { /* TODO */ },
        modifier = Modifier.padding(top = 8.dp),
        textStyle = TextStyle(color = MaterialTheme.colorScheme.secondary)
    )

}
