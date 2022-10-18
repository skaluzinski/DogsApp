package com.example.dogsapp.settings.domain

import com.example.dogsapp.settings.data.SettingQuotesRepository
import javax.inject.Inject



class DeleteAllSavedQuotesUseCase @Inject constructor(private val settingQuotesRepository: SettingQuotesRepository) :
    IDeleteAllSavedQuotesUseCase {
    override suspend fun execute(){
        settingQuotesRepository.deleteAllQuotes()
    }
}