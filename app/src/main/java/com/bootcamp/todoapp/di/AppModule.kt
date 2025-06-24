package com.bootcamp.todoapp.di

import android.content.Context
import androidx.room.Room
import com.bootcamp.todoapp.data.datasource.ToDoDataSource
import com.bootcamp.todoapp.data.repo.ToDoRepository
import com.bootcamp.todoapp.room.Database
import com.bootcamp.todoapp.room.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideToDosRepository(toDoDataSource: ToDoDataSource) : ToDoRepository {
        return ToDoRepository(toDoDataSource)
    }

    @Provides
    @Singleton
    fun provideToDosDataSource(dao: ToDoDao) : ToDoDataSource {
        return ToDoDataSource(dao)
    }

    @Provides
    @Singleton
    fun provideToDosDao(@ApplicationContext context: Context) : ToDoDao {
        val db = Room.databaseBuilder(context, Database::class.java, "ToDo").build()
        return db.toDoDao()
    }


}