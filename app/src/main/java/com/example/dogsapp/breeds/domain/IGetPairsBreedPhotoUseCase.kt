package com.example.dogsapp.breeds.domain

import com.example.dogsapp.breeds.data.remote.dataClasses.DogPhoto

interface IGetPairsBreedPhotoUseCase {
    suspend fun execute(): List<DogPhoto>
}