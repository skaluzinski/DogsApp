package com.example.dogsapp.breeds.di


import com.example.dogsapp.breeds.data.remote.*
import com.example.dogsapp.breeds.domain.repository.GetBreedsWithAllPhotosUseCase
import com.example.dogsapp.breeds.domain.repository.GetPairsBreedPhotoUseCase
import com.example.dogsapp.breeds.domain.repository.IGetBreedsWithAllPhotosUseCase
import com.example.dogsapp.breeds.domain.repository.IGetPairsBreedPhotoUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
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
        return DogsRepositoryImpl(dogsRemoteDataSource)
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
        dogsRepository: IDogsRepository,
        photosRepository: IPhotosRepository

    ): IGetBreedsWithAllPhotosUseCase {
        return GetBreedsWithAllPhotosUseCase(dogsRepository, photosRepository)
    }

    @Provides
    @Singleton
    fun providePairsBreedPhotoUseCase(
        dogsRepository: IDogsRepository,
        photosRepository: IPhotosRepository,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): IGetPairsBreedPhotoUseCase {
        return GetPairsBreedPhotoUseCase(dogsRepository, photosRepository, defaultDispatcher)
    }

    @Provides
    @Singleton
    fun providePhotosRepository(dogsRemoteDataSource: IDogsRemoteDataSource): IPhotosRepository {
        return PhotosRepository(dogsRemoteDataSource)
    }
}