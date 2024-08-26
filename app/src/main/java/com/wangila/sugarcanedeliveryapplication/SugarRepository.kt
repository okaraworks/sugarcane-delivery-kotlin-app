package com.wangila.sugarcanedeliveryapplication


import kotlinx.coroutines.flow.Flow

class SugarcaneRepository(private val sugarcaneDao: SugarcaneDao) {
    val pendingDeliveries: Flow<List<Sugarcane>> = sugarcaneDao.getPendingDeliveries()
    val deliveredHistory: Flow<List<Sugarcane>> = sugarcaneDao.getDeliveredHistory()

    suspend fun insert(sugarcane: Sugarcane) {
        sugarcaneDao.insert(sugarcane)
    }

    suspend fun delete(sugarcane: Sugarcane) {
        sugarcaneDao.delete(sugarcane)
    }

    suspend fun update(sugarcane: Sugarcane) {
        sugarcaneDao.update(sugarcane)
    }
}
