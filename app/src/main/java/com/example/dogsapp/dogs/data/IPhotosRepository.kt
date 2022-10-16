package com.example.dogsapp.dogs.data

import com.example.dogsapp.dogs.data.remote.dataClasses.OneRacePhotos
import com.example.dogsapp.dogs.data.remote.dataClasses.SingleDogResponse

interface IPhotosRepository {
    suspend fun fetchSinglePhotoOfBreed(breedMain: String, breedSub: String?): OneRacePhotos

    suspend fun fetchAllPhotosOfBreed(breedMain: String, breedSub: String?): OneRacePhotos

    suspend fun fetchRandomPhoto(): SingleDogResponse
}