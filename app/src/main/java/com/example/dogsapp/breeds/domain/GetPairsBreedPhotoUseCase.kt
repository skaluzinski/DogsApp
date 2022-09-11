package com.example.dogsapp.breeds.domain

import android.util.Log
import com.example.dogsapp.breeds.data.remote.IDogsRepository
import com.example.dogsapp.breeds.data.remote.IPhotosRepository
import com.example.dogsapp.breeds.data.remote.dataClasses.DogPhoto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetPairsBreedPhotoUseCase @Inject constructor(
    private val dogsRepository: IDogsRepository,
    private val photosRepository: IPhotosRepository,
    private val defaultDispatcher: CoroutineDispatcher
) : IGetPairsBreedPhotoUseCase {
    override suspend fun execute(): List<DogPhoto> =
        withContext(defaultDispatcher) {
            val pairs = mutableListOf<DogPhoto>()
            val breeds = dogsRepository.fetchAllBreeds()
            breeds.forEach { (mainBreed, subBreed) ->
                val name = if (subBreed.isNullOrEmpty()) mainBreed else "$mainBreed-$subBreed"
                val photo =
                    photosRepository
                        .fetchSinglePhotoOfBreed(mainBreed, subBreed)
                        .photos[0]
                pairs.add(DogPhoto(name, photo))
            }
            pairs
        }
}