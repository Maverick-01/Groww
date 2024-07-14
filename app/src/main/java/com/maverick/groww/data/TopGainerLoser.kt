package com.maverick.groww.data

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.gson.annotations.SerializedName
import com.maverick.groww.R

data class TopGainerLoser(
    @field:SerializedName("change_amount")
    val changeAmount: String? = null,
    @field:SerializedName("change_percentage")
    val changePercentage: String? = null,
    @field:SerializedName("price")
    val price: String? = null,
    @field:SerializedName("ticker")
    val ticker: String? = null,
    @field:SerializedName("volume")
    val volume: String? = null,
) {
    fun changeInValue(): String {
        return if (changePercentage?.contains("-") == true) {
            changePercentage
        } else {
            "+$changePercentage"
        }
    }

    fun changeTextColor(context: Context): Int {
        return if (changePercentage?.contains("-") == true) {
            ContextCompat.getColor(context, R.color.red)
        } else {
            ContextCompat.getColor(context, R.color.green)
        }
    }
}