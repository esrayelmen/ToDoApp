package com.bootcamp.todoapp.data.repo

import com.bootcamp.todoapp.data.datasource.ToDoDataSource
import com.bootcamp.todoapp.data.entity.ToDo

class ToDoRepository(var toDoDataSource: ToDoDataSource) {


    suspend fun save(name: String) = toDoDataSource.save(name)

    suspend fun update(id: Int, name: String) = toDoDataSource.update(id, name)

    suspend fun delete(id: Int) = toDoDataSource.delete(id)

    suspend fun loadToDos() : List<ToDo> = toDoDataSource.loadToDos()

    suspend fun search(searchText: String) : List<ToDo> = toDoDataSource.search(searchText)


}