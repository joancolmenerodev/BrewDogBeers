package com.joancolmenerodev.brewdogbeers.base.responses

import com.google.gson.annotations.SerializedName

data class Method(
    @SerializedName("mash_temp") val mashTemp: List<MashTemp>
)



