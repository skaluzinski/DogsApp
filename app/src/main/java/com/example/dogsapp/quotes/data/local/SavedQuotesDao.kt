package com.example.dogsapp.quotes.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


interface SavedQuotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveQuote(item: SavedQuotes)

    @Update
    suspend fun updateQuote(item: SavedQuotes)

    @Delete
    suspend fun deleteQuote(item: SavedQuotes)

    @Query("SELECT * from savedQuotes ORDER BY author DESC")
    fun getSavedQuotes(): Flow<List<SavedQuotes>>
}