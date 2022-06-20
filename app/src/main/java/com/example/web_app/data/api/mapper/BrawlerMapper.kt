package com.example.web_app.data.api.mapper

import android.util.Log
import com.example.web_app.R
import com.example.web_app.data.api.response.BrawlerListResponse
import com.example.web_app.data.api.response.BrawlerResponse
import com.example.web_app.data.api.response.GadgetResponse
import com.example.web_app.data.api.response.StarPowerResponse
import com.example.web_app.domain.entity.Brawler
import com.example.web_app.domain.entity.Gadget
import com.example.web_app.domain.entity.StarPower

class BrawlerMapper {
    fun mapToBrawlers(response: BrawlerListResponse): List<Brawler> {
        return response.items.map {
            Brawler(
                id = it.id,
                name = it.name,
                gadgets = mapToGadget(it.gadgets),
                starPowers = mapToStarPower(it.starPowers),
                imageId = chooseBrawlerImage(it.name)
            )
        }
    }

    fun mapToBrawler(response: BrawlerResponse): Brawler = Brawler(
        id = response.id,
        name = response.name,
        gadgets = mapToGadget(response.gadgets),
        starPowers = mapToStarPower(response.starPowers),
        imageId = chooseBrawlerImage(response.name)
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

    private fun chooseBrawlerImage(name: String): Int {
        return when (name) {
            "SHELLY" -> R.drawable.shelly
            "COLT" -> R.drawable.colt
            "BULL" -> R.drawable.bull
            "BROCK" -> R.drawable.brock
            "RICO" -> R.drawable.rico
            "SPIKE" -> R.drawable.spike
            "BARLEY" -> R.drawable.barley
            "JESSIE" -> R.drawable.jessie
            "NITA" -> R.drawable.nita
            else -> R.drawable.edgar
        }
    }
}