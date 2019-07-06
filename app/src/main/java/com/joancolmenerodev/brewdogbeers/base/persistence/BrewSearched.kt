package com.joancolmenerodev.brewdogbeers.base.persistence

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brewsearched")
data class BrewSearched (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "food")
    val food: String
)