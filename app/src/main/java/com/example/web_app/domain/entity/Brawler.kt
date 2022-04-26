package com.example.web_app.domain.entity

import android.graphics.Bitmap

data class Brawler (
    val id: Int,
    val name: String,
    val gadgets:List<Gadget>,
    val starPowers:List<StarPower>,
    val imageId:Int
)