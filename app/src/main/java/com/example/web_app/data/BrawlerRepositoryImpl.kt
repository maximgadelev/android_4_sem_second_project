package com.example.web_app.data

import android.util.Log
import com.example.web_app.data.api.Api
import com.example.web_app.data.api.mapper.BrawlerMapper
import com.example.web_app.domain.entity.Brawler
import com.example.web_app.domain.repository.BrawlerRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class BrawlerRepositoryImpl @Inject constructor(
    private val api: Api,
    private val mapper: BrawlerMapper
) : BrawlerRepository {
    override fun getBrawlers(): Single<List<Brawler>> = api.getBrawlers()
        .map {
            mapper.mapToBrawlers(it)
        }


    override fun getBrawlerById(id: Int): Single<Brawler> = api.getBrawlerById(id)
        .map {
            mapper.mapToBrawler(it)
        }
}