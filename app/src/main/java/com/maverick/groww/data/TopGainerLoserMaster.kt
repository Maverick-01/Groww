package com.maverick.groww.data

import com.google.gson.annotations.SerializedName

data class TopGainerLoserMaster(
    @field:SerializedName("top_gainers")
    val topGainers: List<TopGainerLoser>? = null,
    @field:SerializedName("top_losers")
    val topLosers: List<TopGainerLoser>? = null,
)