package com.example.dogsapp.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsapp.settings.domain.IDeleteAllSavedQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val deleteAllSavedQuotesUseCase: IDeleteAllSavedQuotesUseCase) :
    ViewModel() {

    fun deleteAllQuotes(toastFunction: (String) -> Unit) {
        viewModelScope.launch {
            try {
                deleteAllSavedQuotesUseCase.execute()
                toastFunction("Deleted all locally saved quotes.")

            } catch (e: Exception) {
            }
        }
    }
}