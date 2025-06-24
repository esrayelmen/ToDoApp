package com.bootcamp.todoapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.todoapp.data.entity.ToDo
import com.bootcamp.todoapp.databinding.ItemTodoBinding
import com.bootcamp.todoapp.ui.screens.MainScreenDirections
import com.bootcamp.todoapp.ui.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class ToDoAdapter(var mContext: Context,
                  var toDoList: List<ToDo>,
                  var viewModel: MainViewModel
) : RecyclerView.Adapter<ToDoAdapter.ItemToDoHolder>()  {

    inner class ItemToDoHolder(var binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemToDoHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ItemToDoHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemToDoHolder, position: Int) {
        val toDo = toDoList[position]
        val view = holder.binding

        view.textViewName.text = toDo.name

        view.cardViewToDo.setOnClickListener {
            val toUpdateScreen = MainScreenDirections.toDetailScreen(toDo)
            it.findNavController().navigate(toUpdateScreen)
        }

        view.imageViewDelete.setOnClickListener {
            Snackbar.make(it, "Do you want to delete ${toDo.name}?", Snackbar.LENGTH_SHORT)
                .setAction("YES") {
                    viewModel.delete(toDo.id)
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }


}