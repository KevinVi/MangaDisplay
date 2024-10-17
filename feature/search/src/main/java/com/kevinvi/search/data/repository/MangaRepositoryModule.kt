package com.kevinvi.search.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MangaRepositoryModule {

    @Binds
    fun bindsMangaRepository(mangaRepositoryImpl: MangaRepostoryImpl): MangaRepository
}