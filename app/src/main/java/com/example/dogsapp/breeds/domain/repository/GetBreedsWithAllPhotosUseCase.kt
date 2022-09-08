package com.example.dogsapp.breeds.domain.repository

import com.example.dogsapp.breeds.data.remote.IDogsRepository
import com.example.dogsapp.breeds.data.remote.IPhotosRepository
import com.example.dogsapp.breeds.data.remote.PhotosRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetBreedsWithAllPhotosUseCase @Inject constructor(
    private val dogsRepository: IDogsRepository,
    private val photosRepository: IPhotosRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : IGetBreedsWithAllPhotosUseCase{
    override suspend fun execute(): Map<String, List<String>> =
        withContext(defaultDispatcher) {
            val pairs = mutableMapOf<String, List<String>>()
            val breeds = dogsRepository.fetchAllBreeds()
            breeds.forEach { (mainBreed, subBreed) ->
                val photos = photosRepository.fetchAllPhotosOfBreed(mainBreed, subBreed).photos
                val name = if (subBreed.isNullOrEmpty()) mainBreed else "$mainBreed-$subBreed"
                pairs[name] = photos
            }
            pairs
        }
}