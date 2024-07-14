package com.maverick.groww.data


import com.google.gson.annotations.SerializedName

data class SearchStock(
    @SerializedName("bestMatches")
    val bestMatches: List<BestMatches?>? = null
)