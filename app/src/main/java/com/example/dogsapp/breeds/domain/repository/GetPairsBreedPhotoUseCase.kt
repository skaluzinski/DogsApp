package com.example.dogsapp.breeds.domain.repository

import com.example.dogsapp.breeds.data.remote.IDogsRepository
import com.example.dogsapp.breeds.data.remote.IPhotosRepository
import com.example.dogsapp.breeds.data.remote.PhotosRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetPairsBreedPhotoUseCase @Inject constructor(
    private val dogsRepository: IDogsRepository,
    private val photosRepository: IPhotosRepository,
    private val defaultDispatcher: CoroutineDispatcher
) : IGetPairsBreedPhotoUseCase {
    override suspend fun execute(): Map<String, String> =
        withContext(defaultDispatcher) {
            val pairs = mutableMapOf<String, String>()
            val breeds = dogsRepository.fetchAllBreeds()
            breeds.forEach { (mainBreed, subBreed) ->
                val photo = photosRepository.fetchSinglePhotoOfBreed(mainBreed, subBreed).photos
                val name = if (subBreed.isNullOrEmpty()) mainBreed else "$mainBreed-$subBreed"
                pairs[name] = photo.toString()
            }
            pairs
        }
}