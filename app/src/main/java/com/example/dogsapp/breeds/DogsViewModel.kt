package com.example.dogsapp.breeds

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

enum class DogsApiStatus { LOADING, ERROR, DONE }

class DogsViewModel : ViewModel() {
    private val _status = MutableLiveData<DogsApiStatus>()
    val status: LiveData<DogsApiStatus> = _status

    private val _breeds = MutableLiveData<Map<String, List<String>>>()
    val breeds: LiveData<Map<String, List<String>>> = _breeds

    init {
        getBreedsList()
        Log.d("dupa","dupa2")
    }

    private fun getBreedsList() {

        viewModelScope.launch {
            _status.value = DogsApiStatus.LOADING
            try {
                val response = DogsApi.retrofitService.getBreedsList()
                if (response.status != "success") {
                    throw BadResponseException(response.status)
                }
                _breeds.value = response.breeds
                _status.value = DogsApiStatus.DONE
            } catch (e: BadResponseException) {
                //Handle bad response,show snackBar or sthing
                _status.value = DogsApiStatus.ERROR
                println(e.message)
            } catch (e: Exception) {
                //Handle unknown exception
                _status.value = DogsApiStatus.ERROR
                println(e.message)
            }
        }
    }
}