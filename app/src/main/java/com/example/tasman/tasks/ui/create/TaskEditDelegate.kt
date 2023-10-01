package com.example.tasman.tasks.ui.create

interface TaskEditDelegate {

    fun updateTitle(title: String)

    fun updateDescription(description: String)

    fun complete()
}