package com.example.android_4_sem_second_project.di.module

import com.example.android_4_sem_second_project.domain.repository.BrawlerRepository
import com.example.android_4_sem_second_project.domain.usecase.GetBrawlerByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideByIdUseCase(
        brawlerRepository: BrawlerRepository
    ) = GetBrawlerByIdUseCase(brawlerRepository)

    @Provides
    @Singleton
    fun provideBrawlersUseCase(
        brawlerRepository: BrawlerRepository
    ) = GetBrawlerByIdUseCase(brawlerRepository)
}