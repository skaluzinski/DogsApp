package com.example.dogsapp.quotes.domain

import com.example.dogsapp.quotes.data.QuotesRepository
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import javax.inject.Inject


class CheckIfQuoteIsSavedUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository
) : ICheckIfQuoteIsSavedUseCase {
    override suspend fun execute(quoteResponse: QuoteResponse): Boolean =
        quotesRepository.checkIfQuoteIsSaved(quoteResponse)
}