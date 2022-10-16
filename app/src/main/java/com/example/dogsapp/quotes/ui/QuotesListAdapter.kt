package com.example.dogsapp.quotes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.R
import com.example.dogsapp.databinding.QuotesListItemBinding
import com.example.dogsapp.quotes.data.local.QuoteState
import com.example.dogsapp.quotes.data.remote.QuoteResponse

class QuotesListAdapter(
    private val savingFuncton: (QuoteResponse) -> Unit,
    private val deletingFunction: (QuoteResponse) -> Unit,
    private val quoteSnackbar: (String) -> Unit
) : ListAdapter<
        QuoteResponse,
        QuotesListAdapter.ViewHolder>(QuotesListAdapter) {
    class ViewHolder(private val binding: QuotesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            quoteResponse: QuoteResponse,
            savingFuncton: (QuoteResponse) -> Unit,
            deletingFunction: (QuoteResponse) -> Unit,
            quoteSnackbar: (String) -> Unit
        ) {

            binding.apply {
                val delim = " "
                val words = quoteResponse.author.split(delim)
                val author = words.joinToString(separator = " ").replace("\\s+".toRegex(), " ")
                if (quoteResponse.isSaved == QuoteState.SAVED) {
                    bookmark.setImageResource(R.drawable.bookmark_filled)
                }
                quoteText.text = quoteResponse.quote
                quoteAuthor.text = author
                bookmark.setOnClickListener {
                    when (quoteResponse.isSaved) {
                        QuoteState.NOT_SAVED -> {
                            bookmark.setImageResource(R.drawable.bookmark_filled)
                            savingFuncton(quoteResponse)
                            quoteResponse.isSaved = QuoteState.SAVED
                            quoteSnackbar("Saved quote of $author.")
                        }
                        QuoteState.SAVED -> {
                            deletingFunction(quoteResponse)
                            quoteResponse.isSaved = QuoteState.NOT_SAVED
                            bookmark.setImageResource(R.drawable.bookmark)
                            quoteSnackbar("Deleted quote of $author.")
                        }
                        else -> quoteSnackbar("This is a quote of $author.")
                    }
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

        holder.bind(quoteResponse, savingFuncton, deletingFunction, quoteSnackbar)
    }
}