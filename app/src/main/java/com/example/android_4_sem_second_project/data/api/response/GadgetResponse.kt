package com.example.android_4_sem_second_project.data.api.response

import com.google.gson.annotations.SerializedName

data class GadgetResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)