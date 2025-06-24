package com.bootcamp.todoapp.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bootcamp.todoapp.R
import com.bootcamp.todoapp.databinding.SaveScreenBinding
import com.bootcamp.todoapp.ui.viewmodel.SaveViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveScreen : Fragment() {

    private lateinit var binding: SaveScreenBinding
    private lateinit var viewModel: SaveViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SaveScreenBinding.inflate(inflater, container, false)

        binding.buttonSave.setOnClickListener {
            val name = binding.editTextName.text.toString()
            viewModel.save(name)
            it.findNavController().navigate(R.id.save_to_main)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SaveViewModel by viewModels()
        viewModel = tempViewModel
    }

}