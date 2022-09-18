package com.example.dogsapp.dogs.data.remote

import com.example.dogsapp.dogs.data.remote.dataClasses.OneRacePhotos
import com.example.dogsapp.dogs.data.remote.dataClasses.SingleDogResponse
import com.example.dogsapp.dogs.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DogsRemoteDataSource @Inject constructor(
    private val dogsApi: DogsApi,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : IDogsRemoteDataSource {
    override suspend fun fetchDogBreedsMap() =
        withContext(defaultDispatcher) {
            dogsApi.getBreedsList().breeds
        }

    override suspend fun fetchMultiplePhotosOfBreed(
        breedMain: String,
        breedSub: String?,
        amount: Int?
    ) =
        withContext(defaultDispatcher) {
            if (breedSub.isNullOrEmpty()) {
                dogsApi.getMainBreedMultiplePhotos(breedMain, amount ?: 1)
            } else {
                dogsApi.getSubBreedMultiplePhotos(breedMain, breedSub, amount ?: 1)
            }
        }

    override suspend fun fetchAllPhotosOfBreed(breedMain: String, breedSub: String?) =
        withContext(defaultDispatcher) {
            if (breedSub.isNullOrEmpty()) {
                dogsApi.getMainBreedAllPhotos(breedMain)
            } else {
                dogsApi.getSubBreedAllPhotos(breedMain, breedSub)
            }
        }

    override suspend fun fetchRandomPhoto(): SingleDogResponse = withContext(defaultDispatcher) {
        dogsApi.getRandomPhoto()
    }
}