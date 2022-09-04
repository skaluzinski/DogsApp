package com.example.dogsapp.mainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dogsapp.R
import com.example.dogsapp.databinding.StartFragmentBinding


class StartFragment : Fragment() {

    private var binding: StartFragmentBinding? = null
    private lateinit var viewModel: StartViewModel

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun navigateToBreeds() {
        findNavController().navigate(R.id.action_startFragment_to_breedsListFragment)
    }
    fun navigateToQuote(){
        findNavController().navigate(R.id.action_startFragment_to_quoteMenuFragment)
    }
}