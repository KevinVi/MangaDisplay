package com.kevinvi.data

import com.kevinvi.data.model.PopularAttributes
import com.kevinvi.data.model.PopularAttributesImage
import com.kevinvi.data.model.PopularData
import com.kevinvi.data.model.PopularDescription
import com.kevinvi.data.model.PopularItem
import com.kevinvi.data.model.PopularRelationships
import org.junit.Assert.assertEquals
import org.junit.Test

class PopularItemModelTest {

    @Test
    fun testPopularItemCreation() {
        val popularItem = PopularItem(
            result = "ok",
            response = "success",
            data = listOf(
                PopularData(
                    id = "1",
                    attributes = PopularAttributes(
                        title = PopularDescription(en = "Title EN", fr = "Title FR"),
                        altTitles = listOf(PopularDescription(en = "Alt Title EN")),
                        description = PopularDescription(en = "Description EN"),
                        createdAt = "2023-10-27T10:00:00Z",
                        updatedAt = "2023-10-27T12:00:00Z",
                        fileName = "filename.jpg",
                        chapter = "1",
                        translatedLanguage = "en",
                        externalUrl = "https://example.com",
                        status = "ongoing",
                        lastVolume = "10",
                        lastChapter = "100",
                        contentRating = "safe"
                    ),
                    relationships = listOf(
                        PopularRelationships(
                            id = "2",
                            type = "cover_art",
                            related = "related_data",
                            attributes = PopularAttributesImage(fileName = "cover.jpg")
                        )
                    )
                )
            )
        )

        assertEquals("ok", popularItem.result)
        assertEquals("success", popularItem.response)
        assertEquals(1, popularItem.data.size)
        // Add more assertions for other properties as needed
    }

    @Test
    fun testPopularDataCreation() {
        val popularData = PopularData(
            id = "1",
            attributes = PopularAttributes(title = PopularDescription(en = "Title EN")),
            relationships = listOf(PopularRelationships(id = "2", type = "cover_art"))
        )

        assertEquals("1", popularData.id)
        assertEquals("Title EN", popularData.attributes?.title?.en)
        assertEquals("cover_art", popularData.relationships?.get(0)?.type)
        // Add more assertions for other properties as needed
    }

    // Add more tests for other data classes (PopularAttributes, PopularRelationships, etc.)
}