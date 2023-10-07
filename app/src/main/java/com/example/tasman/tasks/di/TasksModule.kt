package com.example.tasman.tasks.di

import android.content.Context
import com.example.tasman.tasks.data.room.TasksDao
import com.example.tasman.tasks.data.room.TasksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TasksModule {

    @Provides
    @Singleton
    fun provideTasksDatabase(@ApplicationContext context: Context): TasksDatabase =
        TasksDatabase.getInstance(context)

    @Provides
    fun provideTasksDao(db: TasksDatabase): TasksDao = db.tasksDao()
}