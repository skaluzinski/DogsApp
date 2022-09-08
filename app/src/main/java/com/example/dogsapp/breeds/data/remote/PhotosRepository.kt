package com.example.dogsapp.breeds.data.remote

import com.example.dogsapp.breeds.data.remote.dataClasses.OneRacePhotos
import javax.inject.Inject



class PhotosRepository @Inject constructor(
    private val dogsRemoteDataSource: IDogsRemoteDataSource
) : IPhotosRepository {
    override suspend fun fetchSinglePhotoOfBreed(breedMain: String, breedSub: String?) =
        dogsRemoteDataSource.fetchMultiplePhotosOfBreed(breedMain, breedSub, 1)

    override suspend fun fetchAllPhotosOfBreed(breedMain: String, breedSub: String?) =
        dogsRemoteDataSource.fetchAllPhotosOfBreed(breedMain, breedSub)

}