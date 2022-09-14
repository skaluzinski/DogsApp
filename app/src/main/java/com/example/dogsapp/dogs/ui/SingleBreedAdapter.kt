package com.example.dogsapp.dogs.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.databinding.SingleBreedItemBinding

class SingleBreedAdapter :
    ListAdapter<String,
            SingleBreedAdapter.SingleBreedViewHolder>
        (DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                Log.d("Link", "link")
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                Log.d("Link", "link")
                return oldItem == newItem
            }

        }
    }

    class SingleBreedViewHolder(private var binding: SingleBreedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(link: String) {
            Log.d("Link", link)
            loadImage(binding.photo, link)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleBreedViewHolder {
        val viewHolder = SingleBreedViewHolder(
            SingleBreedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        Log.d("Link", "link")
        viewHolder.itemView.setOnLongClickListener {
            return@setOnLongClickListener true
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SingleBreedViewHolder, position: Int) {
        Log.d("download", "bind")
        holder.bind(getItem(position))
    }
}