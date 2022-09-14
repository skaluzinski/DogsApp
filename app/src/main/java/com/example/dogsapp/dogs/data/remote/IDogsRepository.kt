package com.example.dogsapp.dogs.data.remote

interface IDogsRepository {
    suspend fun fetchAllBreeds(): Map<String, String?>

    suspend fun fetchMainBreeds(): List<String>
}