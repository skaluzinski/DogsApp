package com.example.dogsapp.dogs.ui

import androidx.lifecycle.ViewModel
import com.example.dogsapp.dogs.data.remote.dataClasses.DogPhoto
import com.example.dogsapp.dogs.domain.IGetBreedWithAllPhotosUseCase
import com.example.dogsapp.dogs.domain.IGetPairsBreedPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class DogsViewModel @Inject constructor(
    private val getPairsBreedPhotoUseCase: IGetPairsBreedPhotoUseCase,
    private val getBreedWithAllPhotosUseCase: IGetBreedWithAllPhotosUseCase
) :
    ViewModel() {

    suspend fun dogPhotoPairs(): Flow<DogPhoto> = getPairsBreedPhotoUseCase.execute()

    suspend fun dogBreedPhotos(breed: String): Flow<List<String>>
        {

            val breedParts = breed.split(" ".toRegex()).map { it.trim() }
            println(breedParts[0])
            return getBreedWithAllPhotosUseCase.execute(
                breedParts[0],
                breedParts.getOrNull(1)
            )
        }

}