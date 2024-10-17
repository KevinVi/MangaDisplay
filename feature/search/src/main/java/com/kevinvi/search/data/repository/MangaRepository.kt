package com.kevinvi.search.data.repository

import com.kevinvi.data.model.PopularItem

interface MangaRepository {
    suspend fun getMangaByName(name:String): PopularItem
}