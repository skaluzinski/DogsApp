package com.example.dogsapp.breeds

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsapp.breeds.data.remote.dataClasses.DogPhoto
import com.example.dogsapp.breeds.domain.IGetBreedsWithAllPhotosUseCase
import com.example.dogsapp.breeds.domain.IGetPairsBreedPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

enum class DogsApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class DogsViewModel @Inject constructor(
    getPairsBreedPhotoUseCase: IGetPairsBreedPhotoUseCase,
    getBreedsWithAllPhotosUseCase: IGetBreedsWithAllPhotosUseCase
) :
    ViewModel() {
    private val _status = MutableLiveData<DogsApiStatus>()
    val status: LiveData<DogsApiStatus> = _status

    private val _photos = MutableLiveData<List<DogPhoto>>()
    val photos: LiveData<List<DogPhoto>> get() = _photos

    init {
        getBreedsList(getPairsBreedPhotoUseCase)
    }

    private fun getBreedsList(getPairsBreedPhotoUseCase: IGetPairsBreedPhotoUseCase) {
        viewModelScope.launch {
            _status.value = DogsApiStatus.LOADING
            try {
                _photos.value = getPairsBreedPhotoUseCase.execute()
                photos.value!!.forEach { dogPhoto ->
                    Log.d("urlCheckView",dogPhoto.link.toString())
                }
                _status.value = DogsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = DogsApiStatus.ERROR

                _photos.value = listOf()
            }
        }
    }
}