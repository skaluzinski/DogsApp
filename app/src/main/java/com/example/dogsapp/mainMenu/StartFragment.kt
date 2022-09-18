package com.example.dogsapp.mainMenu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.dogsapp.R
import com.example.dogsapp.databinding.StartFragmentBinding
import com.example.dogsapp.dogs.domain.GetRandomDogPhotoUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import loadImage
import javax.inject.Inject

@AndroidEntryPoint
class StartFragment : Fragment() {
    @Inject
    lateinit var getRandomDogPhotoUseCase: GetRandomDogPhotoUseCase
    private var _binding: StartFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = StartFragmentBinding.inflate(layoutInflater, container, false)
        _binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {
            startFragment = this@StartFragment
        }

        //Decided not to implement viewModel since it would do nothing really needed,just pointless
        // abstraction
        lifecycle.coroutineScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                getRandomDogPhotoUseCase.execute().collect() {
                    loadImage(binding.mainPhoto, it)
                }
                getRandomDogPhotoUseCase.execute().collect() {
                    loadImage(binding.breedsPhoto, it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigateToBreeds() {
        findNavController().navigate(R.id.action_startFragment_to_breedsListFragment)
    }

    fun navigateToQuote() {
        findNavController().navigate(R.id.action_startFragment_to_quoteMenuFragment)
    }

}
