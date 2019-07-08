package com.joancolmenerodev.brewdogbeers.base.persistence

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brew_food")
data class BrewFood (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    val name: String
)