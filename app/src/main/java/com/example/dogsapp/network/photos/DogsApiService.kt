package com.example.dogsapp.network.photos

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://dog.ceo/api/breeds/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface DogsApiService {
    @GET("list/all")
    fun getBreedsList() : String
}

object DogsApi{
    val retrofitService : DogsApiService by lazy {
        retrofit.create(DogsApiService::class.java)
    }
}