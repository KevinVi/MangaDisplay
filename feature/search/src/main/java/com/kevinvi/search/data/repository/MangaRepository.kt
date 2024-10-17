package com.kevinvi.search.data.repository

import com.kevinvi.popular.data.model.PopularItem

interface MangaRepository {
    suspend fun getMangaByName(name:String): PopularItem
}