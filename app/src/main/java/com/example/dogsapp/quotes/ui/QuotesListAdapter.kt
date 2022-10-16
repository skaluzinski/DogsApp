package com.example.dogsapp.quotes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.databinding.QuotesListItemBinding
import com.example.dogsapp.quotes.data.local.QuoteState
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow

class QuotesListAdapter(
    private val savingFuncton: (QuoteResponse) -> Unit,
    //private val quoteCheckingFunction: (QuoteResponse) -> Flow<Boolean>,
    private val quoteSnackbar: (String) -> Unit
) : ListAdapter<
        QuoteResponse,
        QuotesListAdapter.ViewHolder>(QuotesListAdapter) {
    class ViewHolder(private val binding: QuotesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            quoteResponse: QuoteResponse,
            savingFuncton: (QuoteResponse) -> Unit,
//            quoteCheckingFunction: (QuoteResponse) -> Boolean,
            quoteSnackbar: (String) -> Unit
        ) {
            binding.apply {
                quoteText.text = quoteResponse.quote
                val delim = " "
                val words = quoteResponse.author.trim().split(delim)
                val author = words.joinToString(separator = " ")

                quoteAuthor.text = author
                root.setOnLongClickListener {
                    savingFuncton(quoteResponse)
                    when(quoteResponse.isSaved){
                        QuoteState.NOT_SAVED -> quoteSnackbar("Saved quote of $author.")
                        QuoteState.SAVED -> quoteSnackbar("This quote is already saved.")
                        else -> quoteSnackbar("This is a quote of $author.")
                    }
                    return@setOnLongClickListener true
                }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<QuoteResponse>() {
        override fun areItemsTheSame(oldItem: QuoteResponse, newItem: QuoteResponse): Boolean {
            return oldItem.author == newItem.author
        }

        override fun areContentsTheSame(oldItem: QuoteResponse, newItem: QuoteResponse): Boolean {
            return oldItem.quote == newItem.quote
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            QuotesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quoteResponse = getItem(position)

        holder.bind(quoteResponse, savingFuncton, quoteSnackbar)
    }
}