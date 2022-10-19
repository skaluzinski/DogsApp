package com.example.dogsapp.settings.domain

import com.example.dogsapp.settings.data.SettingsQuotesRepository
import javax.inject.Inject



class DeleteAllSavedQuotesUseCase @Inject constructor(private val settingsQuotesRepository: SettingsQuotesRepository) :
    IDeleteAllSavedQuotesUseCase {
    override suspend fun execute(){
        settingsQuotesRepository.deleteAllQuotes()
    }
}