package com.example.dogsapp.dogs.data.remote

import com.example.dogsapp.dogs.data.remote.dataClasses.OneRacePhotos
import com.example.dogsapp.dogs.data.remote.dataClasses.SingleDogResponse

interface IDogsRemoteDataSource {
    suspend fun fetchDogBreedsMap(): Map<String, List<String>>

    suspend fun fetchMultiplePhotosOfBreed(breedMain: String, breedSub: String?, amount: Int?): OneRacePhotos

    suspend fun fetchAllPhotosOfBreed(breedMain: String, breedSub: String?): OneRacePhotos

    suspend fun fetchRandomPhoto(): SingleDogResponse
}