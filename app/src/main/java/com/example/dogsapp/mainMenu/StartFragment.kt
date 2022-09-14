package com.example.dogsapp.mainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.dogsapp.R
import com.example.dogsapp.databinding.StartFragmentBinding


class StartFragment : Fragment() {

    private var binding: StartFragmentBinding? = null
    private val startViewmodel: StartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = StartFragmentBinding.inflate(layoutInflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            startFragment = this@StartFragment
        }
        //loadImage(binding!!.photoCircle,startViewmodel.photos.value!!.get(0))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    fun navigateToBreeds() {
        findNavController().navigate(R.id.action_startFragment_to_breedsListFragment)
    }

    fun navigateToQuote() {
        findNavController().navigate(R.id.action_startFragment_to_quoteMenuFragment)
    }

    private fun loadImage(imageView: ImageView, imageLink: String) {
        Glide
            .with(imageView.context)
            .load(imageLink)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_connection_error)
            .into(binding!!.breedsPhoto)
    }
}
