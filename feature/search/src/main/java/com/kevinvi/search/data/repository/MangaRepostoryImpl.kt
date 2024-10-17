package com.kevinvi.search.data.repository

import android.util.Log
import com.kevinvi.data.KtorClient.client
import com.kevinvi.popular.data.model.PopularItem
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import javax.inject.Inject

class MangaRepostoryImpl @Inject constructor() : MangaRepository {
    private val json = Json { ignoreUnknownKeys = true }
    override suspend fun getMangaByName(name: String): PopularItem {

        val response: HttpResponse =
            client.get("https://api.mangadex.org/manga?title=$name&includes[]=cover_art&contentRating[]=safe&limit=40")

        val responseBody = response.bodyAsText()
        return json.decodeFromString<PopularItem>(responseBody)
    }
}