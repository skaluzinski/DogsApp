package com.example.dogsapp.dogs.domain

import com.example.dogsapp.dogs.data.remote.dataClasses.OneRacePhotos

interface IGetRandomDogPhotoUseCase {
    suspend fun execute(): String
}
