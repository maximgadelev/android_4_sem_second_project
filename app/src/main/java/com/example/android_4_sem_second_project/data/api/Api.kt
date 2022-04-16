package com.example.android_4_sem_second_project.data.api

import com.example.android_4_sem_second_project.data.api.response.BrawlerResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("brawlers")
    fun getBrawlers(): Single<List<BrawlerResponse>>
    @GET("brawlers")
    fun getBrawlerById(@Query("brawlerId") id:Int):Single<BrawlerResponse>
}