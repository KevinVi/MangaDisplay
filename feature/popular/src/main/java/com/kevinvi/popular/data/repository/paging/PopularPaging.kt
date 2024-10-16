package com.kevinvi.scan.data.repository.paging

import android.net.http.HttpException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kevinvi.data.room.KtorClient.client
import com.kevinvi.scan.data.model.ScanData
import com.kevinvi.scan.data.model.ScanItem
import com.kevinvi.scan.data.repository.ScanRepository
import com.kevinvi.scan.data.repository.ScanRepositoryImpl
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import java.io.IOException

class PopularPaging(val scanRepository: ScanRepository): PagingSource<Int, ScanData>() {

    private val json = Json { ignoreUnknownKeys = true }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ScanData> {
        return try {
            val currentPage = params.key ?: 0

            val offset = currentPage * 20
            val test = scanRepository.getLastUpdateList(offset)
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

    override fun getRefreshKey(state: PagingState<Int, ScanData>): Int? {
        return state.anchorPosition
    }

}