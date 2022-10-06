package com.example.dogsapp.dogs.domain

import com.example.dogsapp.dogs.data.remote.IDogsRepository
import com.example.dogsapp.dogs.data.remote.IPhotosRepository
import com.example.dogsapp.dogs.data.remote.dataClasses.DogPhoto
import com.example.dogsapp.dogs.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetPairsBreedPhotoUseCase @Inject constructor(
    private val dogsRepository: IDogsRepository,
    private val photosRepository: IPhotosRepository,
    private val defaultDispatcher: CoroutineDispatcher
) : IGetPairsBreedPhotoUseCase {
    override suspend fun execute(): Flow<DogPhoto> =
        flow {
            val breeds = dogsRepository.fetchAllBreeds()
            breeds.forEach { (mainBreed, subBreed) ->
                val name = if (subBreed.isNullOrEmpty()) mainBreed else "$mainBreed $subBreed"
                val photo =
                    photosRepository
                        .fetchSinglePhotoOfBreed(mainBreed, subBreed)
                        .photos[0]
                emit(DogPhoto(name, photo))

            }
        }
}
