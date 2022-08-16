package com.example.dogsapp.network.photos

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://dog.ceo/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DogsApiService {
    @GET("breeds/list/all")
    suspend fun getBreedsList(): BreedList

    @GET("breed/{breed}/images/random/{amount}")
    suspend fun getBreedPhotos(
        @Path("breed") breedName: String,
        @Path("amount") amountOfPhotos: Int = 1
    ): DogsPhoto


    @GET("breed/{breed}/images")
    suspend fun getMainBreedAllPhotos(
        @Path("breed") breedName: String
    ): DogsPhoto

    @GET("breed/{breed}/{subBreed}/images/")
    suspend fun getSubBreedAllPhotos(
        @Path("breed") breedName: String,
        @Path("subBreed") subBreedName: String
    ): DogsPhoto
}

object DogsApi {
    val retrofitService: DogsApiService by lazy {
        retrofit.create(DogsApiService::class.java)
    }
}