package com.example.dogsapp.breeds.data.remote

import com.example.dogsapp.breeds.data.remote.dataClasses.OneRacePhotos

interface IDogsRemoteDataSource {
    suspend fun fetchDogBreedsMap(): Map<String, List<String>>

    suspend fun fetchMultiplePhotosOfBreed(breedMain: String, breedSub: String?, amount: Int?): OneRacePhotos

    suspend fun fetchAllPhotosOfBreed(breedMain: String, breedSub: String?): OneRacePhotos
}