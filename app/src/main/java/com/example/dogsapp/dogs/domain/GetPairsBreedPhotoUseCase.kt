package com.example.dogsapp.dogs.domain

import com.example.dogsapp.dogs.data.IDogsRepository
import com.example.dogsapp.dogs.data.IPhotosRepository
import com.example.dogsapp.dogs.data.remote.dataClasses.DogPhoto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
