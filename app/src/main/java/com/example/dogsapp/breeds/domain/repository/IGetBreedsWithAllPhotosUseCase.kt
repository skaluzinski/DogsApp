package com.example.dogsapp.breeds.domain.repository

interface IGetBreedsWithAllPhotosUseCase {
    suspend fun execute(): Map<String, List<String>>
}