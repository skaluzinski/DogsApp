package com.example.dogsapp.quotes.domain

import com.example.dogsapp.quotes.data.IQuotesRepository
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetAllQuotesUseCase @Inject constructor(
    private val quotesRepository: IQuotesRepository
    ) :
    IGetAllQuotesUseCase {
    override suspend fun execute(): Flow<List<QuoteResponse>> = flow{
        emit(quotesRepository.fetchAllQuotes())
    }
}