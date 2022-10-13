package com.example.dogsapp.quotes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.databinding.QuotesListFragmentBinding
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuotesListFragment : Fragment() {
    private val quoteViewModel: QuoteViewModel by viewModels()
    private var _binding: QuotesListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private val listener by lazy { requireActivity() as QuotesListFragment.quoteSnackbar }

    interface quoteSnackbar {
        fun show(message: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = QuotesListFragmentBinding.inflate(inflater, container, false)
        _binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.quotesListRv
        val quotesAdapter = QuotesListAdapter(
            {
                quoteViewModel.saveQuote(it)
            },
            {
                quoteViewModel.searchForQuote(it)
            },
            {
                listener.show(it)
            }
        )

        recyclerView.adapter = quotesAdapter
        lifecycle.coroutineScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                quoteViewModel.fetchAllQuotes().collect() {
                    quotesAdapter.submitList(it)
                }
                binding.progressIndicator.visibility = View.GONE
            }
        }

    }


}