package com.joancolmenerodev.brewdogbeers.base.responses

import com.google.gson.annotations.SerializedName

data class Temp(
    @SerializedName("value") val value: Int,
    @SerializedName("unit") val unit: String
)