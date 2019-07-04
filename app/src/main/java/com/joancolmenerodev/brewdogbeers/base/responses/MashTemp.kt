package com.joancolmenerodev.brewdogbeers.base.responses

import com.google.gson.annotations.SerializedName

data class MashTemp(
    @SerializedName("temp") val temp: Temp,
    @SerializedName("duration") val duration: Int
)