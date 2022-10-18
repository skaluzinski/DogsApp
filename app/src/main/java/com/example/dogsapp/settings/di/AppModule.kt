package com.example.dogsapp.settings.di

import com.example.dogsapp.dogs.di.DefaultDispatcher
import com.example.dogsapp.quotes.data.local.ISavedQuotesLocalDataSource
import com.example.dogsapp.quotes.data.local.SavedQuotesDao
import com.example.dogsapp.settings.data.SettingQuotesRepository
import com.example.dogsapp.settings.data.local.SavedQuotesLocalDataSource
import com.example.dogsapp.settings.domain.DeleteAllSavedQuotesUseCase
import com.example.dogsapp.settings.domain.IDeleteAllSavedQuotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSavedQuotesLocalDataSource(savedQuotesDao: SavedQuotesDao): ISavedQuotesLocalDataSource {
        return SavedQuotesLocalDataSource(savedQuotesDao)
    }

    @Provides
    @Singleton
    fun provideSettingsQuotesRepository(
        savedQuotesLocalDataSource: ISavedQuotesLocalDataSource,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): SettingQuotesRepository {
        return SettingQuotesRepository(savedQuotesLocalDataSource,defaultDispatcher)
    }

    @Provides
    @Singleton
    fun provideDeleteAllQuotesUseCase(settingQuotesRepository: SettingQuotesRepository): IDeleteAllSavedQuotesUseCase {
        return DeleteAllSavedQuotesUseCase(settingQuotesRepository)
    }
}