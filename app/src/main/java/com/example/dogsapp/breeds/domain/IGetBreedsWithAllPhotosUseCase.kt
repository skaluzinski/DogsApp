package com.example.dogsapp.breeds.domain

interface IGetBreedsWithAllPhotosUseCase {
    suspend fun execute(): Map<String, List<String>>
}