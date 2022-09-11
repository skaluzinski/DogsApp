package com.example.dogsapp

import android.media.Image
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dogsapp.breeds.DogsApiStatus
import com.example.dogsapp.breeds.data.remote.dataClasses.DogPhoto
import com.example.dogsapp.breeds.ui.BreedsListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DogPhoto>?) {
    val adapter = recyclerView.adapter as BreedsListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Log.d("linkCheck",imgUrl)
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Log.d("uriCheck",imgUri.toString())
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_connection_error)

        }
    }
}

@BindingAdapter("dogsApiStatus")
fun bindStatus(statusImageView: ImageView, status: DogsApiStatus?) {
    when (status) {
        DogsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        DogsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        DogsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        /*else ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }*/
    }

}