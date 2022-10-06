package com.example.dogsapp.quotes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.databinding.QuotesListItemBinding
import com.example.dogsapp.quotes.data.remote.QuoteResponse

class QuotesListAdapter(
    private val onItemClicked: (QuoteResponse) -> Unit
) : ListAdapter<
        QuoteResponse,
        QuotesListAdapter.ViewHolder>(QuotesListAdapter) {
    class ViewHolder(private val binding: QuotesListItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(quoteResponse: QuoteResponse,onItemClicked: (QuoteResponse) -> Unit){
            binding.quoteAuthor.text = quoteResponse.author
            binding.quoteText.text = quoteResponse.quote
            binding.root.setOnLongClickListener {
                onItemClicked(quoteResponse)
                return@setOnLongClickListener true
            }
        }
    }
    companion object DiffCallback :  DiffUtil.ItemCallback<QuoteResponse>(){
        override fun areItemsTheSame(oldItem: QuoteResponse, newItem: QuoteResponse): Boolean {
            return oldItem.author == newItem.author
        }

        override fun areContentsTheSame(oldItem: QuoteResponse, newItem: QuoteResponse): Boolean {
            return oldItem.quote == newItem.quote
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
            QuotesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        viewHolder.itemView.setOnLongClickListener {
            Toast.makeText(parent.context,"long click",Toast.LENGTH_SHORT).show()
            true
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quoteResponse = getItem(position)
        holder.bind(quoteResponse,onItemClicked)
    }
}