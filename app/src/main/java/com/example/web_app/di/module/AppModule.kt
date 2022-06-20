package com.example.web_app.di.module

import com.example.web_app.data.api.mapper.BrawlerMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideMapper(): BrawlerMapper = BrawlerMapper()
}