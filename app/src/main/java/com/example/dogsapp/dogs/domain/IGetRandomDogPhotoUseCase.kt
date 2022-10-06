package com.example.dogsapp.dogs.domain

import com.example.dogsapp.dogs.data.remote.dataClasses.OneRacePhotos
import kotlinx.coroutines.flow.Flow

interface IGetRandomDogPhotoUseCase {
    suspend fun execute(): Flow<String>
}
