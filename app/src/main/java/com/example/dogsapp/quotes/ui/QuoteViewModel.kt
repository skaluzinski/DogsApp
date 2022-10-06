package com.example.dogsapp.quotes.ui

import androidx.lifecycle.ViewModel
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import com.example.dogsapp.quotes.domain.IGetAllQuotesUseCase
import com.example.dogsapp.quotes.domain.IGetRandomQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getAllQuotesUseCase: IGetAllQuotesUseCase,
    private val getRandomQuoteUseCase: IGetRandomQuoteUseCase
) :
    ViewModel() {
    suspend fun fetchAllQuotes() : Flow<List<QuoteResponse>> = getAllQuotesUseCase.execute()

    suspend fun fetchRandomQuote(): Flow<QuoteResponse> = getRandomQuoteUseCase.execute()
}