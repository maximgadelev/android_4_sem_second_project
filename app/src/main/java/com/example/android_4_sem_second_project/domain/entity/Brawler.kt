package com.example.android_4_sem_second_project.domain.entity

data class Brawler (
    val id: Int,
    val name: String,
    val gadgets:List<Gadget>,
    val starPowers:List<StarPower>
)