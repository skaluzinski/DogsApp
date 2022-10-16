package com.example.dogsapp.quotes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import com.example.dogsapp.quotes.domain.ICheckIfQuoteIsSavedUseCase
import com.example.dogsapp.quotes.domain.IGetAllQuotesUseCase
import com.example.dogsapp.quotes.domain.IGetRandomQuoteUseCase
import com.example.dogsapp.quotes.domain.ISaveQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
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
    private val _queryState = MutableStateFlow("")
    val queryState: MutableStateFlow<String> = _queryState


    suspend fun fetchAllQuotes(): Flow<List<QuoteResponse>> = getAllQuotesUseCase.execute()

    suspend fun fetchRandomQuote(): Flow<QuoteResponse> = getRandomQuoteUseCase.execute()

    fun saveQuote(quoteResponse: QuoteResponse) {
        viewModelScope.launch() {
            saveQuoteUseCase.execute(quoteResponse)
        }
    }

    fun searchForQuote(quoteResponse: QuoteResponse) =
        flow { emit(checkIfQuoteIsSavedUseCase.execute(quoteResponse)) }


}
