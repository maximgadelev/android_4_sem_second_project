package com.example.web_app.data.api.response

import com.google.gson.annotations.SerializedName

data class BrawlerResponse(
    @SerializedName("gadgets")
    val gadgets: List<GadgetResponse>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("starPowers")
    val starPowers: List<StarPowerResponse>
)