package com.example.android_4_sem_second_project.di.module

import com.example.android_4_sem_second_project.data.api.mapper.BrawlerMapper
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