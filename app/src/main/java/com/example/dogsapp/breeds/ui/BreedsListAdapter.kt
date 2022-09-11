package com.example.dogsapp.breeds.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.breeds.data.remote.dataClasses.DogPhoto
import com.example.dogsapp.databinding.BreedsListFragmentBinding
import com.example.dogsapp.databinding.BreedsListItemBinding

class BreedsListAdapter : ListAdapter<
        DogPhoto,
        BreedsListAdapter.ViewHolder>(DiffCallback) {


    class ViewHolder(private var binding: BreedsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dogsPhoto: DogPhoto?) {
            binding.photo = dogsPhoto
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<DogPhoto>() {
        override fun areItemsTheSame(oldItem: DogPhoto, newItem: DogPhoto): Boolean {
            return oldItem.breed == newItem.breed
        }

        override fun areContentsTheSame(oldItem: DogPhoto, newItem: DogPhoto): Boolean {
            return oldItem.link == newItem.link
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BreedsListItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dogPhoto = getItem(position)
        holder.bind(dogPhoto)
    }

}
