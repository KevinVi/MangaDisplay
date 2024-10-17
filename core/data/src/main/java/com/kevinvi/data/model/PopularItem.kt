package com.kevinvi.data.model

import com.kevinvi.common.extensions.empty
import kotlinx.serialization.Serializable

@Serializable
data class PopularItem(
    val result: String,
    val response: String,
    val data: List<PopularData>,
)

@Serializable
data class PopularData(
    val id: String,
    val attributes: PopularAttributes? = null,
    val relationships: List<PopularRelationships>? = null,
)

@Serializable
data class PopularAttributes(
    val title: PopularDescription? = null,
    val altTitles: List<PopularDescription>? = null,
    val description: PopularDescription? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val fileName: String? = null,
    val chapter: String? = null,
    val translatedLanguage: String? = null,
    val externalUrl: String? = null,
    val status: String? = null,
    val lastVolume: String? = null,
    val lastChapter: String? = null,
    val contentRating: String? = null,
)

@Serializable
data class PopularAttributesImage(
    val fileName: String? = null,
)

@Serializable
data class PopularRelationships(
    val id: String,
    val type: String? = null,
    val related: String? = String.Companion.empty,
    val attributes: PopularAttributesImage? = null,
)

@Serializable
data class PopularDescription(
    val en: String? = null,
    val fr: String? = null,
)

