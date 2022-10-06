package com.example.dogsapp.quotes.domain

import com.example.dogsapp.quotes.data.remote.QuoteResponse
import kotlinx.coroutines.flow.Flow

interface IGetAllQuotesUseCase {
    suspend fun execute(): Flow<List<QuoteResponse>>
}