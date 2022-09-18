package com.example.dogsapp.dogs.data.remote

import com.example.dogsapp.dogs.data.remote.dataClasses.BreedsList
import com.example.dogsapp.dogs.data.remote.dataClasses.OneRacePhotos
import com.example.dogsapp.dogs.data.remote.dataClasses.SingleDogResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApi {
    @GET("breeds/list/all")
    suspend fun getBreedsList(): BreedsList

    @GET("breed/{breed}/images/random/{amount}")
    suspend fun getMainBreedMultiplePhotos(
        @Path("breed") breedName: String,
        @Path("amount") amountOfPhotos: Int = 1
    ): OneRacePhotos


    @GET("breed/{breed}/images")
    suspend fun getMainBreedAllPhotos(
        @Path("breed") breedName: String
    ): OneRacePhotos

    @GET("breed/{breed}/{subBreed}/images/random/{amount}")
    suspend fun getSubBreedMultiplePhotos(
        @Path("breed") breedName: String,
        @Path("subBreed") subBreedName: String,
        @Path("amount") amountOfPhotos: Int = 1
    ): OneRacePhotos

    @GET("breed/{breed}/{subBreed}/images/")
    suspend fun getSubBreedAllPhotos(
        @Path("breed") breedName: String,
        @Path("subBreed") subBreedName: String
    ): OneRacePhotos

    @GET("breeds/image/random")
    suspend fun getRandomPhoto(): SingleDogResponse
}