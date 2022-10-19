package com.example.dogsapp.settings.di

import com.example.dogsapp.quotes.data.AppDatabase
import com.example.dogsapp.settings.data.ISettingQuotesRepository
import com.example.dogsapp.settings.data.SettingsQuotesRepository
import com.example.dogsapp.settings.data.local.ISettingsQuotesDao
import com.example.dogsapp.settings.data.local.ISettingsSavedQuotesLocalDataSource
import com.example.dogsapp.settings.data.local.SavedQuotesLocalDataSource
import com.example.dogsapp.settings.domain.DeleteAllSavedQuotesUseCase
import com.example.dogsapp.settings.domain.IDeleteAllSavedQuotesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideSettingsQuotesDao(settingsQuotesDatabase: AppDatabase): ISettingsQuotesDao {
        return settingsQuotesDatabase.SettingsQuotesDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractSettingsModule {

    @Binds
    @Singleton
    abstract fun bindSettingsSavedQuotesLocalDB(savedQuotesLocalDataSource: SavedQuotesLocalDataSource): ISettingsSavedQuotesLocalDataSource

    @Binds
    @Singleton
    abstract fun bindSettingsQuotesRepository(settingsQuotesRepository: SettingsQuotesRepository): ISettingQuotesRepository

    @Binds
    @Singleton
    abstract fun bindDeleteAllQuotesUseCase(deleteAllSavedQuotesUseCase: DeleteAllSavedQuotesUseCase): IDeleteAllSavedQuotesUseCase
}

