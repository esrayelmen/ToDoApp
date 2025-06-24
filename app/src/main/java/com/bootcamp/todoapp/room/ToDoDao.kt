package com.bootcamp.todoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bootcamp.todoapp.data.entity.ToDo

@Dao
interface ToDoDao {

    @Query("SELECT * FROM toDos")
    suspend fun loadToDos() : List<ToDo>

    @Insert
    suspend fun save(toDo: ToDo)

    @Query("UPDATE toDos SET name=:name WHERE id=:id")
    suspend fun update(id: Int, name: String)

    //@Query("DELETE FROM toDos WHERE id=:id")
    @Delete
    suspend fun delete(toDo: ToDo)

    @Query("SELECT * FROM toDos WHERE name like '%' || :searchText || '%'")
    suspend fun search(searchText: String) : List<ToDo>

}