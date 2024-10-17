package com.kevinvi.popular.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kevinvi.data.KtorClient.client
import com.kevinvi.popular.data.model.PopularData
import com.kevinvi.popular.data.model.PopularItem
import com.kevinvi.popular.data.repository.paging.PopularPaging
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor() : PopularRepository {
    private val json = Json { ignoreUnknownKeys = true }
    private val url =
        "https://api.mangadex.org/manga?includes[]=cover_art&includes[]=artist&includes[]=author&contentRating[]=safe&hasAvailableChapters=true&availableTranslatedLanguage[]=fr&limit=20&offset="

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getLastUpdateList(offset: Int): PopularItem {

        val urlWithOffset = url + offset
        Log.d("TAG", "getLastUpdateList: $urlWithOffset")
        val response: HttpResponse =
            client.get(urlWithOffset)

        val responseBody = response.bodyAsText()
        return json.decodeFromString<PopularItem>(responseBody)
    }

    override suspend fun getPopular(): Flow<PagingData<PopularData>> {
        return Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 3)) {
            PopularPaging(this)
        }.flow
    }

}