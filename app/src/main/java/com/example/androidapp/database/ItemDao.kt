package com.example.androidapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import androidx.room.OnConflictStrategy

@Dao
interface ItemDao {
    //crud

    @Insert
    suspend fun insert(groceryItem: Item)

    @Update
    suspend   fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from item WHERE itemId = :itemId")
    fun getItem(itemId: Int): Flow<Item>

    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>

    @Query("DELETE FROM item")
    suspend fun clearAllItems()

}