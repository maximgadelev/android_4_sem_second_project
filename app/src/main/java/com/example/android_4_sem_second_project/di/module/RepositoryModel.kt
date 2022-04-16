package com.example.android_4_sem_second_project.di.module

import com.example.android_4_sem_second_project.data.BrawlerRepositoryImpl
import com.example.android_4_sem_second_project.domain.repository.BrawlerRepository
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