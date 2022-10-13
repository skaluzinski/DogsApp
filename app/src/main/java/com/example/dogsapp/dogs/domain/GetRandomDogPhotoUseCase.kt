package com.example.dogsapp.dogs.domain

import com.example.dogsapp.dogs.data.IPhotosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetRandomDogPhotoUseCase @Inject constructor(
    private val photosRepository: IPhotosRepository
) : IGetRandomDogPhotoUseCase {
    override suspend fun execute(): Flow<String> =
        flow{
            emit(photosRepository.fetchRandomPhoto().link)
        }
}