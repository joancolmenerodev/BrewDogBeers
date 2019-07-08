package com.joancolmenerodev.brewdogbeers.base.persistence

import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "brewbeer")
data class BrewBeer(
    @PrimaryKey @NonNull @ColumnInfo(name = "beer_id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "tag_line") val tagline: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "abv") val abv: Double,
    @ColumnInfo(name = "pairing_food") val pairingFood : List<String>
)