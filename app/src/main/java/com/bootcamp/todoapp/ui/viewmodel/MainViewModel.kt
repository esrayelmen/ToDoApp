package com.bootcamp.todoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.todoapp.data.entity.ToDo
import com.bootcamp.todoapp.data.repo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var toDoRepository: ToDoRepository) : ViewModel() {

    var toDoList = MutableLiveData<List<ToDo>>()

    init {
        loadToDos()
    }

    fun delete(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            toDoRepository.delete(id)
            loadToDos()
        }
    }

    fun loadToDos() {
        CoroutineScope(Dispatchers.Main).launch {
            toDoList.value = toDoRepository.loadToDos()
        }
    }

    fun search(searchText: String) {
        CoroutineScope(Dispatchers.Main).launch {
            toDoList.value = toDoRepository.search(searchText)
        }
    }
}