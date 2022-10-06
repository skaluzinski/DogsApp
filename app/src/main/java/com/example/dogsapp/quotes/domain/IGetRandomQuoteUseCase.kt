package com.example.dogsapp.quotes.domain

import com.example.dogsapp.quotes.data.remote.QuoteResponse
import kotlinx.coroutines.flow.Flow

interface IGetRandomQuoteUseCase {
    suspend fun execute(): Flow<QuoteResponse>
}