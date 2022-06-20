package com.example.web_app.domain.repository

import com.example.web_app.domain.entity.Brawler
import io.reactivex.rxjava3.core.Single

interface BrawlerRepository {
fun getBrawlers(): Single<List<Brawler>>
fun getBrawlerById(id:Int):Single<Brawler>
}