package com.example.dogsapp.breeds.data.remote

import com.example.dogsapp.breeds.data.remote.dataClasses.OneRacePhotos
import com.example.dogsapp.breeds.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject



class DogsRemoteDataSource @Inject constructor(
    private val dogsApi: DogsApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : IDogsRemoteDataSource {
    override suspend fun fetchDogBreedsMap() =
        withContext(ioDispatcher) {
            dogsApi.getBreedsList().breeds
        }

    override suspend fun fetchMultiplePhotosOfBreed(breedMain: String, breedSub: String?, amount: Int?) =
        withContext(ioDispatcher) {
            if (breedSub.isNullOrEmpty()) {
                dogsApi.getMainBreedMultiplePhotos(breedMain, amount ?: 1)
            } else {
                dogsApi.getSubBreedMultiplePhotos(breedMain, breedSub,amount ?: 1)
            }
        }

    override suspend fun fetchAllPhotosOfBreed(breedMain: String, breedSub: String?) =
        withContext(ioDispatcher) {
            if (breedSub.isNullOrEmpty()) {
                dogsApi.getMainBreedMultiplePhotos(breedMain)
            } else {
                dogsApi.getSubBreedAllPhotos(breedMain, breedSub)
            }
        }
}