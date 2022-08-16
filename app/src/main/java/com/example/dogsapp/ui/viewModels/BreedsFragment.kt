package com.example.dogsapp.ui.viewModels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dogsapp.databinding.FragmentMainBinding

class BreedsFragment : Fragment() {


    private val viewModel: BreedsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        return binding.root
    }


}