package com.example.android_4_sem_second_project.domain.repository

import com.example.android_4_sem_second_project.domain.entity.Brawler
import io.reactivex.rxjava3.core.Single

interface BrawlerRepository {
fun getBrawlers(): Single<List<Brawler>>
fun getBrawlerById(id:Int):Single<Brawler>
}