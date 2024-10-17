package com.kevinvi.popular.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kevinvi.data.model.PopularData
import com.kevinvi.popular.data.repository.PopularRepository
import kotlinx.serialization.json.Json
import java.io.IOException

class PopularPaging(val popularRepository: PopularRepository): PagingSource<Int, PopularData>() {

    private val json = Json { ignoreUnknownKeys = true }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularData> {
        return try {
            val currentPage = params.key ?: 0

            val offset = currentPage * 20
            val test = popularRepository.getLastUpdateList(offset)
            test.data

            LoadResult.Page(
                data = test.data,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (test.data.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PopularData>): Int? {
        return state.anchorPosition
    }

}