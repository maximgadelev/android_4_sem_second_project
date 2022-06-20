package com.example.web_app.data.api.response

import com.google.gson.annotations.SerializedName

data class BrawlerListResponse(
    @SerializedName(value = "items")
    val items: List<BrawlerResponse>,
)