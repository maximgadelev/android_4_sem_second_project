package com.example.web_app.data.api

import com.example.web_app.data.api.response.BrawlerListResponse
import com.example.web_app.data.api.response.BrawlerResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("brawlers")
    fun getBrawlers(): Single<BrawlerListResponse>

    @GET("brawlers/{brawlerId}")
    fun getBrawlerById(@Path("brawlerId") id: Int): Single<BrawlerResponse>
}