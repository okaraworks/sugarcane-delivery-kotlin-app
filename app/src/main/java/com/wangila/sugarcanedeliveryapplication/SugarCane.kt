package com.wangila.sugarcanedeliveryapplication


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sugarcane(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,
    val farmer: String,
    val driver: String,
    val weight: Double,
    val location: String,
    val delivered: Boolean = false
)
