package com.kevinvi.popular.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PopularRepositoryModule {

    @Binds
    fun bindsScanRepository(scanRepositoryImpl: PopularRepositoryImpl): PopularRepository
}