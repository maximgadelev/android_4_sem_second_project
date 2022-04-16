package com.example.android_4_sem_second_project.data.api.mapper

import com.example.android_4_sem_second_project.data.api.response.BrawlerResponse
import com.example.android_4_sem_second_project.data.api.response.GadgetResponse
import com.example.android_4_sem_second_project.data.api.response.StarPowerResponse
import com.example.android_4_sem_second_project.domain.entity.Brawler
import com.example.android_4_sem_second_project.domain.entity.Gadget
import com.example.android_4_sem_second_project.domain.entity.StarPower

class BrawlerMapper {
    fun mapToBrawlers(response: List<BrawlerResponse>): List<Brawler> {
        return response.map {
            Brawler(
                id = it.id,
                name = it.name,
                gadgets = mapToGadget(it.gadgets),
                starPowers = mapToStarPower(it.starPowers)
            )
        }
    }

    fun mapToBrawler(response: BrawlerResponse): Brawler = Brawler(
        id = response.id,
        name = response.name,
        gadgets = mapToGadget(response.gadgets),
        starPowers = mapToStarPower(response.starPowers)
    )

    private fun mapToGadget(gadgetResponse: List<GadgetResponse>): List<Gadget> {
        return gadgetResponse.map { gadget ->
            Gadget(
                id = gadget.id,
                name = gadget.name
            )
        }
    }

    private fun mapToStarPower(starPowerResponse: List<StarPowerResponse>): List<StarPower> {
        return starPowerResponse.map { starPower ->
            StarPower(
                id = starPower.id,
                name = starPower.name
            )
        }
    }
}