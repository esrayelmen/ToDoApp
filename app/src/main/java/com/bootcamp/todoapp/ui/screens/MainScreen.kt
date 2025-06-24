package com.bootcamp.todoapp.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.todoapp.R
import com.bootcamp.todoapp.databinding.MainScreenBinding
import com.bootcamp.todoapp.ui.adapter.ToDoAdapter
import com.bootcamp.todoapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : Fragment() {

    private lateinit var binding: MainScreenBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainScreenBinding.inflate(inflater, container, false)

        viewModel.toDoList.observe(viewLifecycleOwner) {
            val toDoAdapter = ToDoAdapter(requireContext(), it, viewModel)
            binding.recyclerViewToDos.adapter = toDoAdapter
        }

        binding.recyclerViewToDos.layoutManager = LinearLayoutManager(requireContext())

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(newText: String): Boolean {
                viewModel.search(newText)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                viewModel.search(query)
                return true
            }

        })

        binding.fab.setOnClickListener {
            it.findNavController().navigate(R.id.toSaveScreen)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadToDos()
    }
}