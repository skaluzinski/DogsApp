package com.example.dogsapp.quotes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import com.example.dogsapp.quotes.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getAllQuotesUseCase: IGetAllQuotesUseCase,
    private val getRandomQuoteUseCase: IGetRandomQuoteUseCase,
    private val saveQuoteUseCase: ISaveQuoteUseCase,
    private val checkIfQuoteIsSavedUseCase: ICheckIfQuoteIsSavedUseCase
) :
    ViewModel() {
    suspend fun fetchAllQuotes(): Flow<List<QuoteResponse>> = getAllQuotesUseCase.execute()

    suspend fun fetchRandomQuote(): Flow<QuoteResponse> = getRandomQuoteUseCase.execute()

    fun saveQuote(quoteResponse: QuoteResponse) {
        viewModelScope.launch() {
            saveQuoteUseCase.execute(quoteResponse)
        }
    }

    suspend fun searchForQuote(quoteResponse: QuoteResponse) =
        checkIfQuoteIsSavedUseCase.execute(quoteResponse)


}
