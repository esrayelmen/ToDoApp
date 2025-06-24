package com.bootcamp.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.bootcamp.todoapp.data.repo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var toDoRepository: ToDoRepository) : ViewModel() {

    fun update(id: Int, name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            toDoRepository.update(id, name)
        }
    }

}