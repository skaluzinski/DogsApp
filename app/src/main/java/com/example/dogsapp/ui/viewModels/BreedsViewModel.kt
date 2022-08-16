package com.example.dogsapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsapp.network.photos.DogsApi
import com.example.dogsapp.network.photos.BreedList
import com.example.dogsapp.network.photos.DogsPhoto
import kotlinx.coroutines.launch
import java.lang.Exception

class BreedsViewModel : ViewModel() {
    private val _breedsList = MutableLiveData<BreedList>()
    val breedsList: LiveData<BreedList?> get() = _breedsList
    private val _photo =  MutableLiveData<DogsPhoto>()
    val photo: String? get() = _photo.value!!.photos[0]


    init {
        getBreedsList()
        //test()
    }


    private fun getBreedsList() {
        viewModelScope.launch {
            try {
                _photo.value = DogsApi.retrofitService.getMainBreedAllPhotos("hound")
                println(_photo.value)
            } catch (e: Exception) {

            }
        }

    }

    fun test() {

        //_breedsList.value = listResult
        viewModelScope.launch {
            val listResult = DogsApi.retrofitService.getBreedsList()
            listResult.breeds.forEach { s, list ->
                viewModelScope.launch {
                    /*if (list.isNotEmpty()) {
                        list.forEach {
                            val photos = DogsApi.retrofitService.getBreedAllPhotos(s)
                            Log.d("checkContent3", photos.toString())
                        }
                    } else {
                        val photos = DogsApi.retrofitService.getBreedAllPhotos(s)
                        Log.d("checkContent3", photos.toString())
                    }*/
                }
            }
        }
    }
}
