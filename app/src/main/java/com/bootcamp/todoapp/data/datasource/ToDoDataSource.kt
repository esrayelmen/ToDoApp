package com.bootcamp.todoapp.data.datasource

import com.bootcamp.todoapp.data.entity.ToDo
import com.bootcamp.todoapp.room.ToDoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToDoDataSource(var toDoDao: ToDoDao) {

    suspend fun save(name: String) {
        val newToDo = ToDo(0, name)
        toDoDao.save(newToDo)
    }

    suspend fun update(id: Int, name: String) {
        toDoDao.update(id, name)
    }

    suspend fun delete(id: Int) {
        val toDo = ToDo(id, "")
        toDoDao.delete(toDo)
    }

    suspend fun loadToDos() : List<ToDo> = withContext(Dispatchers.IO) {
        return@withContext toDoDao.loadToDos()
    }

    suspend fun search(searchText: String) : List<ToDo> = withContext(Dispatchers.IO) {
        return@withContext toDoDao.search(searchText)
    }
}