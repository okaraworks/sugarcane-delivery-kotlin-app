package com.wangila.sugarcanedeliveryapplication

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SugarcaneDao {
    @Query("SELECT * FROM Sugarcane WHERE delivered = 0")
    fun getPendingDeliveries(): Flow<List<Sugarcane>>

    @Query("SELECT * FROM Sugarcane WHERE delivered = 1")
    fun getDeliveredHistory(): Flow<List<Sugarcane>>

    @Insert
    suspend fun insert(sugarcane: Sugarcane)

    @Delete
    suspend fun delete(sugarcane: Sugarcane)

    @Update
    suspend fun update(sugarcane: Sugarcane)
}
