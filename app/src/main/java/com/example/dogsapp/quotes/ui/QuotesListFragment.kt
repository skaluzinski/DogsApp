package com.example.dogsapp.quotes.ui

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
import com.example.dogsapp.databinding.QuotesListFragmentBinding
import com.example.dogsapp.quotes.data.local.QuoteState
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuotesListFragment : Fragment() {
    private val quoteViewModel: QuoteViewModel by viewModels()
    private var _binding: QuotesListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private val notificationPresenter by lazy { requireActivity() as quoteSnackbar }

    interface quoteSnackbar {
        fun quoteSnackbar(message: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = QuotesListFragmentBinding.inflate(inflater, container, false)
        _binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.quotesListRv
        val quotesAdapter = QuotesListAdapter(
            ::saveQuote,
            ::deleteQuote,
            notificationPresenter::quoteSnackbar
        )

        recyclerView.adapter = quotesAdapter

        lifecycle.coroutineScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                quoteViewModel.fetchAllQuotes().collect() { quotesList ->
                    quotesList.forEach { quote ->
                        quoteViewModel.searchForQuote(quote).collect() { quoteState ->
                            if (quoteState) {
                                quote.isSaved = QuoteState.SAVED
                            } else {
                                quote.isSaved = QuoteState.NOT_SAVED
                            }
                        }
                    }
                    quotesAdapter.submitList(quotesList)
                }
                binding.progressIndicator.visibility = View.GONE
            }
        }

    }

    //Todo change current way to reactive state paradigm with accordance
    //https://www.youtube.com/watch?v=PH9_FjiiZvo 37:30
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveQuote(quoteResponse: QuoteResponse) = quoteViewModel.saveQuote(quoteResponse)

    private fun deleteQuote(quoteResponse: QuoteResponse) =
        quoteViewModel.deleteQuote(quoteResponse)

}