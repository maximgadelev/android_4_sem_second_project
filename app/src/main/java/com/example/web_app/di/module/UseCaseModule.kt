package com.example.web_app.di.module

import com.example.web_app.domain.repository.BrawlerRepository
import com.example.web_app.domain.usecase.GetBrawlerByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    @Named("byId")
    fun provideByIdUseCase(
        brawlerRepository: BrawlerRepository
    ) = GetBrawlerByIdUseCase(brawlerRepository)

    @Provides
    @Singleton
    @Named("byBrawlers")
    fun provideBrawlersUseCase(
        brawlerRepository: BrawlerRepository
    ) = GetBrawlerByIdUseCase(brawlerRepository)
}