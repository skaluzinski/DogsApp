package com.example.dogsapp.dogs.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.databinding.BreedsListItemBinding
import com.example.dogsapp.dogs.data.remote.dataClasses.DogPhoto

class BreedsListAdapter(
    private val onItemClicked: (DogPhoto) -> Unit,
    private val imageLoadingFunction: (ImageView, String) -> Unit
) : ListAdapter<
        DogPhoto,
        BreedsListAdapter.ViewHolder>(DiffCallback) {


    class ViewHolder(private var binding: BreedsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            dogsPhoto: DogPhoto,
            loadImage: (ImageView, String) -> Unit
        ) {

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
        holder.bind(dogPhoto,imageLoadingFunction)
    }

}

