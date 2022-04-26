package com.example.web_app.di.module

import com.example.web_app.data.BrawlerRepositoryImpl
import com.example.web_app.domain.repository.BrawlerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModel {

     @Binds
     fun brawlerRepository(
        impl: BrawlerRepositoryImpl
    ): BrawlerRepository
}