package com.example.dogsapp.quotes.domain

import com.example.dogsapp.quotes.data.QuotesRepository
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import com.example.dogsapp.quotes.data.remote.QuotesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository
    ):IGetRandomQuoteUseCase {
    override suspend fun execute(): Flow<QuoteResponse> = flow{
        emit(quotesRepository.fetchSingleQuote())
    }
}