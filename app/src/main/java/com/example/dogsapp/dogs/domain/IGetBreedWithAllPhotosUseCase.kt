package com.example.dogsapp.dogs.domain

import kotlinx.coroutines.flow.Flow

interface IGetBreedWithAllPhotosUseCase {
    suspend fun execute(mainBreed: String, subBreed: String?): Flow<List<String>>
}