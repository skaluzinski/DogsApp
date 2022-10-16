package com.example.dogsapp.quotes.data.local

import androidx.room.*

@Dao
interface SavedQuotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuote(item: SavedQuotes)

    @Update
    suspend fun updateQuote(item: SavedQuotes)

    @Delete
    suspend fun deleteQuote(item: SavedQuotes)

    @Query("SELECT * FROM savedQuotes")
    fun getSavedQuotes(): List<SavedQuotes>

    @Query("DELETE FROM savedQuotes")
    fun deleteSavedQuotes()

    @Query("SELECT id FROM savedQuotes WHERE author LIKE :authorToFind AND quote LIKE :quoteToFind")
    fun getIdOfQuote(authorToFind: String, quoteToFind: String): Int

    @Query("SELECT EXISTS(SELECT * FROM savedQuotes WHERE author LIKE :authorToFind AND quote LIKE :quoteToFind)")
    fun checkIfSaved(authorToFind: String, quoteToFind: String): Boolean
}