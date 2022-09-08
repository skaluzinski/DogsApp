package com.example.dogsapp.breeds.data.remote

interface IDogsRepository {
    suspend fun fetchAllBreeds(): Map<String, String?>

    suspend fun fetchMainBreeds(): List<String>
}