package com.kevinvi.search

import com.kevinvi.search.data.repository.MangaRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockEngine.Companion.invoke
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MangaRepositoryImplTest {

    private lateinit var repository: MangaRepositoryImpl
    private lateinit var mockHttpClient: HttpClient

    @Before
    fun setup() {
        val mockEngine = MockEngine { request ->
            respond(
                content = """
                    {
                      "result": "ok",
                      "response": "collection",
                      "data": [
                        {
                          "id": "1",
                          "type": "manga",
                          "attributes": {
                            "title": {
                              "en": "Test Manga"
                            }
                          }
                        }
                      ]
                    }
                """.trimIndent(),
                status = HttpStatusCode.OK,
                headers = headersOf(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json.toString()
                )
            )
        }

        mockHttpClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }

        repository = MangaRepositoryImpl(mockHttpClient) // Inject mockHttpClient
    }

    @Test
    fun testGetMangaByName() = runBlocking {
        val mangaName = "Test Manga"
        val result = repository.getMangaByName(mangaName)

        assertEquals("ok", result.result)
        assertEquals("collection", result.response)
        assertEquals("1", result.data[0].id)
        assertEquals("Test Manga", result.data[0].attributes?.title?.en)
    }
}