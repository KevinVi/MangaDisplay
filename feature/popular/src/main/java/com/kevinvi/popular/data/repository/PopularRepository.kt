package com.kevinvi.popular.data.repository

import androidx.paging.PagingData
import com.kevinvi.data.model.PopularData
import com.kevinvi.data.model.PopularItem
import kotlinx.coroutines.flow.Flow

interface PopularRepository {
    suspend fun getLastUpdateList(offset: Int): PopularItem

    suspend fun getPopular(): Flow<PagingData<PopularData>>
}