package com.example.dogsapp.quotes.domain

import com.example.dogsapp.quotes.data.remote.QuoteResponse

interface ISaveQuoteUseCase {
    suspend fun execute(quoteResponse: QuoteResponse)
}