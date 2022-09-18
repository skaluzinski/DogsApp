package com.example.dogsapp.dogs.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.dogsapp.R
import com.example.dogsapp.dogs.data.remote.dataClasses.DogPhoto
import com.example.dogsapp.databinding.BreedsListItemBinding
import loadImage

class BreedsListAdapter(
    private val onItemClicked: (DogPhoto) -> Unit
) : ListAdapter<
        DogPhoto,
        BreedsListAdapter.ViewHolder>(DiffCallback) {


    class ViewHolder(private var binding: BreedsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dogsPhoto: DogPhoto) {

            binding.breedPhoto.apply {
                setOnClickListener {
                    val navController = Navigation.findNavController(binding.root)
                    val action =
                        BreedsListFragmentDirections.actionBreedsListFragmentToBreedPhotos(dogsPhoto.breed)
                    navController.navigate(action)
                }
            }
            loadImage(binding.breedPhoto, dogsPhoto.link)
            binding.breedName.text = dogsPhoto.breed
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
        val viewHolder = ViewHolder(
            BreedsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        viewHolder.itemView.setOnLongClickListener {
            true
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dogPhoto = getItem(position)
        holder.bind(dogPhoto)
    }

}

