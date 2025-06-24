package com.bootcamp.todoapp.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bootcamp.todoapp.R
import com.bootcamp.todoapp.databinding.DetailScreenBinding
import com.bootcamp.todoapp.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreen : Fragment() {

    private lateinit var binding: DetailScreenBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailScreenBinding.inflate(inflater, container, false)

        val bundle: DetailScreenArgs by navArgs()
        val toDo = bundle.toDo

        binding.editTextName.setText(toDo.name)

        binding.buttonUpdate.setOnClickListener {
            val name = binding.editTextName.text.toString()
            viewModel.update(toDo.id, name)
            it.findNavController().navigate(R.id.detail_to_main)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

}