package com.example.dogsapp.dogs.domain

import com.example.dogsapp.dogs.data.remote.IPhotosRepository
import com.example.dogsapp.dogs.data.remote.dataClasses.OneRacePhotos
import com.example.dogsapp.dogs.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetRandomDogPhotoUseCase @Inject constructor(
    private val photosRepository: IPhotosRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : IGetRandomDogPhotoUseCase {
    override suspend fun execute() =
        withContext(defaultDispatcher) {
            photosRepository.fetchRandomPhoto().photos[0]
        }

}