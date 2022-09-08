package com.example.dogsapp.breeds

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsapp.breeds.domain.repository.IGetBreedsWithAllPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class DogsApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class DogsViewModel @Inject constructor(
    getPairsBreedPhotoUseCase: IGetBreedsWithAllPhotosUseCase,
    getBreedsWithAllPhotosUseCase: IGetBreedsWithAllPhotosUseCase
) :
    ViewModel() {

    private fun getDogsAndPhoto(){
        viewModelScope.launch {

        }
    }
}