package com.example.dogsapp.dogs.domain

import com.example.dogsapp.dogs.data.remote.dataClasses.DogPhoto
import kotlinx.coroutines.flow.Flow

interface IGetPairsBreedPhotoUseCase {
    suspend fun execute(): Flow<DogPhoto>
}