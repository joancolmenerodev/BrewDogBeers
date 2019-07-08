package com.joancolmenerodev.brewdogbeers.base.persistence

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "pairing_food")
data class ParingFood(
    var food: String,
    @ForeignKey(
        entity = BrewBeer::class,
        parentColumns = ["id"],
        childColumns = ["id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    ) var id: Int
)