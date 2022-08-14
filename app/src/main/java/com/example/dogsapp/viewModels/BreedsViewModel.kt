package com.example.dogsapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsapp.network.photos.DogsApi
import kotlinx.coroutines.launch

class BreedsViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> get() = _status

    init {
        getBreedsList()
    }

    private fun getBreedsList() {
        viewModelScope.launch {
            val listResult = DogsApi.retrofitService.getBreedsList()
            _status.value = listResult
        }
    }
}