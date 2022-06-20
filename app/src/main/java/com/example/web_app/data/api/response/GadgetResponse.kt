package com.example.web_app.data.api.response

import com.google.gson.annotations.SerializedName

data class GadgetResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)