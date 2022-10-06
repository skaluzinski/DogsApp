package com.example.dogsapp.dogs.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.dogsapp.dogs.DogsViewModel
import com.example.dogsapp.databinding.BreedsListFragmentBinding
import com.example.dogsapp.dogs.data.remote.dataClasses.DogPhoto
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BreedsListFragment : Fragment() {
    private val sharedViewModel: DogsViewModel by viewModels()
    private var _binding: BreedsListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = BreedsListFragmentBinding.inflate(inflater, container, false)
        fragmentBinding.lifecycleOwner = viewLifecycleOwner

        _binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = sharedViewModel
        recyclerView = binding.breedsListRv
        //recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        val breedsListAdapter = BreedsListAdapter(
            {
                val action = BreedsListFragmentDirections.actionBreedsListFragmentToBreedPhotos(
                    it.breed
                )
                view.findNavController().navigate(action)
            }
        )
        val newList = mutableListOf<DogPhoto>()
        recyclerView.adapter = breedsListAdapter
        lifecycle.coroutineScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedViewModel.dogPhotoPairs().collect() {
                    newList.add(it)
                    breedsListAdapter.submitList(newList.toList())
                }
                binding.progressIndicator.visibility = View.GONE
            }

        }

        recyclerView.addItemDecoration(
            BreedsItemDecoration()
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()

    }

}