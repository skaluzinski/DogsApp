package com.example.dogsapp.dogs.domain

import android.util.Log
import com.example.dogsapp.dogs.data.remote.IPhotosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBreedWithAllPhotosUseCase @Inject constructor(
    private val photosRepository: IPhotosRepository
) : IGetBreedWithAllPhotosUseCase {
    override suspend fun execute(mainBreed: String, subBreed: String?): Flow<List<String>> =
        flow {
            try {
                Log.d("dupa3",photosRepository.fetchAllPhotosOfBreed(mainBreed, subBreed).photos.toString())
                emit(photosRepository.fetchAllPhotosOfBreed(mainBreed, subBreed).photos)
            } catch (e: Exception) {
                Log.d("error","$mainBreed + $subBreed")
            }
        }
}