package com.example.dogsapp.mainMenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsapp.dogs.domain.IGetRandomDogPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(getRandomDogPhotoUseCase: IGetRandomDogPhotoUseCase) :
    ViewModel(
    ) {
    private val _photos = MutableLiveData<List<String>>()
    val photos: LiveData<List<String>> get() = _photos

    init {
        val list = mutableListOf<String>()
        list.add(getSinglePhoto(getRandomDogPhotoUseCase))
        list.add(getSinglePhoto(getRandomDogPhotoUseCase))
    }

    private fun getSinglePhoto(getRandomDogPhotoUseCase: IGetRandomDogPhotoUseCase ): String {
        var link = ""
        viewModelScope.launch {
            try {
                link = getRandomDogPhotoUseCase.execute()
            } catch (e: Exception) {

            }
        }
        return link
    }
    // TODO: Implement the ViewModel
}