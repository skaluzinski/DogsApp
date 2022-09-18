package com.example.dogsapp.dogs.data.remote

import com.example.dogsapp.dogs.data.remote.dataClasses.OneRacePhotos
import com.example.dogsapp.dogs.data.remote.dataClasses.SingleDogResponse
import javax.inject.Inject


class PhotosRepository @Inject constructor(
    private val dogsRemoteDataSource: IDogsRemoteDataSource
) : IPhotosRepository {
    override suspend fun fetchSinglePhotoOfBreed(breedMain: String, breedSub: String?) =
        dogsRemoteDataSource.fetchMultiplePhotosOfBreed(breedMain, breedSub, 1)

    override suspend fun fetchAllPhotosOfBreed(breedMain: String, breedSub: String?) =
        dogsRemoteDataSource.fetchAllPhotosOfBreed(breedMain, breedSub)

    override suspend fun fetchRandomPhoto(): SingleDogResponse =
        dogsRemoteDataSource.fetchRandomPhoto()

}