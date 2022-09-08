package com.example.dogsapp.breeds.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.dogsapp.breeds.DogsViewModel
import com.example.dogsapp.databinding.BreedsListFragmentBinding


class BreedsListFragment : Fragment() {
    private val sharedViewModel: DogsViewModel by activityViewModels()
    private var binding: BreedsListFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = BreedsListFragmentBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.viewModel = sharedViewModel
    }

    fun navigateToBreedPhotos() {
        val action = BreedsListFragmentDirections.actionBreedsListFragmentToBreedPhotos("it works!")
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}