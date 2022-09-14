package com.example.dogsapp.dogs.data.remote

import com.example.dogsapp.dogs.data.remote.dataClasses.OneRacePhotos

interface IPhotosRepository {
    suspend fun fetchSinglePhotoOfBreed(breedMain: String, breedSub: String?): OneRacePhotos

    suspend fun fetchAllPhotosOfBreed(breedMain: String, breedSub: String?): OneRacePhotos

    suspend fun fetchRandomPhoto(): OneRacePhotos
}