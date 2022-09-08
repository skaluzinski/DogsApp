package com.example.dogsapp.breeds.domain.repository

interface IGetPairsBreedPhotoUseCase {
    suspend fun execute(): Map<String, String>
}