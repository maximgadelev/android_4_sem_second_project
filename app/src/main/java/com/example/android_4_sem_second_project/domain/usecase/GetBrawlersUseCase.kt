package com.example.android_4_sem_second_project.domain.usecase

import com.example.android_4_sem_second_project.domain.entity.Brawler
import com.example.android_4_sem_second_project.domain.repository.BrawlerRepository
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