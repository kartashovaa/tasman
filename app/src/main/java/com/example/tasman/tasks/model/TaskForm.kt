package com.example.tasman.tasks.model

data class TaskForm(
    val title: String = "",
    val description: String = "",
) {

    val isValid: Boolean = title.isNotBlank()
}