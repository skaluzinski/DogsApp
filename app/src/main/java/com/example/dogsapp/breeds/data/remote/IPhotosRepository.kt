package com.example.dogsapp.breeds.data.remote

import com.example.dogsapp.breeds.data.remote.dataClasses.OneRacePhotos

interface IPhotosRepository {
    suspend fun fetchSinglePhotoOfBreed(breedMain: String, breedSub: String?): OneRacePhotos

    suspend fun fetchAllPhotosOfBreed(breedMain: String, breedSub: String?): OneRacePhotos
}