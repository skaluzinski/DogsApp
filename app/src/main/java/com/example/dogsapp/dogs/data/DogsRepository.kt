package com.example.dogsapp.dogs.data

import com.example.dogsapp.dogs.data.remote.IDogsRemoteDataSource
import javax.inject.Inject

class DogsRepository @Inject constructor(
    private val dogsRemoteDataSource: IDogsRemoteDataSource
) : IDogsRepository {
    override suspend fun fetchAllBreeds(): Map<String, String?> {
        val breedsList = mutableMapOf<String, String?>()
        val breeds = dogsRemoteDataSource.fetchDogBreedsMap()

        breeds.forEach { (mainBreed, subBreeds) ->
            breedsList.put(mainBreed, null)
            if (subBreeds.isNotEmpty()) {
                subBreeds.forEach { subBreed ->
                    breedsList.put(mainBreed, subBreed)
                }
            }
        }
        return breedsList
    }

    override suspend fun fetchMainBreeds(): List<String> {
        val breedsList = mutableListOf<String>()
        val breeds = dogsRemoteDataSource.fetchDogBreedsMap()
        breeds.forEach { (mainBreed, _) ->
            breedsList.add(mainBreed)
        }
        return breedsList
    }

}