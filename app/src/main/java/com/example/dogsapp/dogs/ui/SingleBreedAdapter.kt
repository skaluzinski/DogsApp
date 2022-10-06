package com.example.dogsapp.dogs.ui

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.databinding.SingleBreedItemBinding

class SingleBreedAdapter(
    private val breedName: String,
    private val imageLoadingFunction: (ImageView, String) -> Unit,
    private val imageSavingFunction: (Bitmap, Context, String) -> Unit
) :
    ListAdapter<String,
            SingleBreedAdapter.SingleBreedViewHolder>
        (DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }

    class SingleBreedViewHolder(private var binding: SingleBreedItemBinding, breedName: String) :
        RecyclerView.ViewHolder(binding.root) {
        val breed = breedName
        fun bind(
            link: String,
            breedName: String,
            loadImage: (ImageView, String) -> Unit,
            saveImage: (Bitmap, Context, String) -> Unit
        ) {
            loadImage(binding.photo, link)
            binding.photo.setOnLongClickListener {
                val bitmap = binding.photo.drawable.toBitmap()
                saveImage(bitmap, binding.root.context, "dogPhotos/$breedName")
                return@setOnLongClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleBreedViewHolder {
        val viewHolder = SingleBreedViewHolder(
            SingleBreedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), breedName
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: SingleBreedViewHolder, position: Int) {
        holder.bind(getItem(position), holder.breed, imageLoadingFunction, imageSavingFunction)
    }
}