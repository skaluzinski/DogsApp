package com.example.dogsapp.dogs.di


import com.example.dogsapp.dogs.data.remote.*
import com.example.dogsapp.dogs.domain.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://dog.ceo/api/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDogsApi(moshi: Moshi): DogsApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
            .create(DogsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideDogRepository(dogsRemoteDataSource: IDogsRemoteDataSource): IDogsRepository {
        return DogsRepository(dogsRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideDogsRemoteDataSource(
        dogsApi: DogsApi,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): IDogsRemoteDataSource {
        return DogsRemoteDataSource(dogsApi, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideDogsWithAllPhotosUseCase(
        photosRepository: IPhotosRepository

    ): IGetBreedWithAllPhotosUseCase {
        return GetBreedWithAllPhotosUseCase(photosRepository)
    }

    @Provides
    @Singleton
    fun providePairsBreedPhotoUseCase(
        dogsRepository: IDogsRepository,
        photosRepository: IPhotosRepository,
        @IoDispatcher defaultDispatcher: CoroutineDispatcher
    ): IGetPairsBreedPhotoUseCase {
        return GetPairsBreedPhotoUseCase(dogsRepository, photosRepository, defaultDispatcher)
    }

    @Provides
    @Singleton
    fun providePhotosRepository(dogsRemoteDataSource: IDogsRemoteDataSource): IPhotosRepository {
        return PhotosRepository(dogsRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideRandomDogUseCase(
        photosRepository: IPhotosRepository
    ): IGetRandomDogPhotoUseCase {
        return GetRandomDogPhotoUseCase(photosRepository)
    }
}