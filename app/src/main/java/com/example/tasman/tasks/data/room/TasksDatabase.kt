package com.example.tasman.tasks.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tasman.tasks.model.Task

@Database(
    entities = [
        Task::class
    ],
    exportSchema = false,
    version = 1,
)
abstract class TasksDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao

    companion object {
        private const val DATABASE_NAME = "tasks"

        fun getInstance(context: Context): TasksDatabase {
            return Room.databaseBuilder(context, TasksDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}