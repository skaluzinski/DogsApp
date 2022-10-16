package com.example.dogsapp.quotes.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedQuotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveQuote(item: SavedQuotes)

    @Update
    suspend fun updateQuote(item: SavedQuotes)

    @Delete
    suspend fun deleteQuote(item: SavedQuotes)

    @Query("SELECT * FROM savedQuotes")
    fun getSavedQuotes(): List<SavedQuotes>

    @Query("DELETE FROM savedQuotes")
    fun deleteSavedQuotes()

    @Query("SELECT EXISTS(SELECT * FROM savedQuotes WHERE author LIKE :authorToFind AND quote LIKE :quoteToFind)")
    fun checkIfSaved(authorToFind: String, quoteToFind: String): Boolean
}