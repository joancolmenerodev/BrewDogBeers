package com.joancolmenerodev.brewdogbeers.base.persistence.room.dto

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.NOCASE
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brew_food")
data class BrewFood (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name", collate = NOCASE)
    val name: String
)