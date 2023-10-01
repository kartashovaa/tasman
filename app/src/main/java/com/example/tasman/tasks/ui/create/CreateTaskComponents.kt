package com.example.tasman.tasks.ui.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tasman.tasks.model.TaskForm
import com.example.tasman.tasks.ui.preview.lorem

@Composable
fun CreateTaskScreen(
    viewModel: CreateTaskViewModel = hiltViewModel(),
    onCompleted: () -> Unit = {}
) {
    val form by viewModel.form.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.isFormCompleted.collect { onCompleted() }
    }
    CreateTaskScreenBody(form, viewModel)
}

@Composable
@Preview(showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
fun CreateTaskScreenBody(
    @PreviewParameter(TaskFormPreviewParameterProvider::class)
    form: TaskForm,
    editDelegate: TaskEditDelegate? = null
) = Column {
    TopAppBar(title = { Text("New task") })
    TextField(
        value = form.title,
        placeholder = { Text("Title") },
        singleLine = true,
        onValueChange = { editDelegate?.updateTitle(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .padding(horizontal = 16.dp)
    )

    //TODO: pushes button out of the screen
    TextField(
        value = form.description,
        placeholder = { Text("Description") },
        onValueChange = { editDelegate?.updateDescription(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .padding(horizontal = 16.dp)
    )
    Button(
        enabled = form.isValid,
        onClick = { editDelegate?.complete() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text("Complete")
    }
}


private class TaskFormPreviewParameterProvider : PreviewParameterProvider<TaskForm> {
    override val values: Sequence<TaskForm> = sequenceOf(
        TaskForm(),
        TaskForm("Lorem ipsum", "Some task description"),
        TaskForm(lorem, lorem),
    )
}
