package com.example.dogsapp.quotes.domain

import com.example.dogsapp.quotes.data.IQuotesRepository
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import javax.inject.Inject


class CheckIfQuoteIsSavedUseCase @Inject constructor(
    private val quotesRepository: IQuotesRepository
) : ICheckIfQuoteIsSavedUseCase {
    override suspend fun execute(quoteResponse: QuoteResponse): Boolean =
        quotesRepository.checkIfQuoteIsSaved(quoteResponse)
}