package com.example.dogsapp.quotes.data.local

interface ISavedQuotesDatabase {
    fun getSavedQuotesDao(): SavedQuotesDao
}