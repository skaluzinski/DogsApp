package com.example.dogsapp.dogs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.databinding.SingleBreedPhotosFragmentBinding
import com.example.dogsapp.dogs.DogsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import loadImage
import saveImage

@AndroidEntryPoint
class SingleBreedPhotos : Fragment() {
    private val sharedViewModel: DogsViewModel by viewModels()
    private var _binding: SingleBreedPhotosFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var breedName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            breedName = it.getString("breed").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = SingleBreedPhotosFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        _binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = sharedViewModel
        recyclerView = binding.breedPhotosRv
        val singleBreedAdapter = SingleBreedAdapter(breedName, ::loadImage, ::saveImage)
        recyclerView.adapter = singleBreedAdapter
        lifecycle.coroutineScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedViewModel.dogBreedPhotos(breedName).collect() {
                    singleBreedAdapter.submitList(it)
                }
                binding.progressIndicator.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
