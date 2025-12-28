package com.mustafa.products.data.response

import com.google.gson.annotations.SerializedName

data class RatingResponseDto(
    @SerializedName("rate")
    val rate: Double,
    @SerializedName("count")
    val count: Int
)


