package com.example.web_app.domain.usecase

import com.example.web_app.domain.entity.Brawler
import com.example.web_app.domain.repository.BrawlerRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetBrawlersUseCase @Inject constructor(
    private val brawlerRepository: BrawlerRepository
) {
    operator fun invoke(): Single<List<Brawler>> = brawlerRepository.getBrawlers().subscribeOn(
        Schedulers.io()
    )
}