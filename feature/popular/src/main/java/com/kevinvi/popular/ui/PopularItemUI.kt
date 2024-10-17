package com.kevinvi.popular.ui

import android.os.Parcelable
import com.kevinvi.common.data.UiModel
import com.kevinvi.common.extensions.empty
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class PopularItemUI(
    val id: String,
    val title: String?,
    val altTitles: String?,
    val description: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val image: String?,
    val lastChapter: String? = String.empty,
    val isFinished: Boolean ,
    val listLinkedId: List<Pair<String,String>>,
    val contentRating: String?
) : UiModel, Parcelable {

    companion object {
        val EMPTY = PopularItemUI(
            id = String.empty,
            title = null,
            altTitles = null,
            description = null,
            createdAt = null,
            updatedAt = null,
            image = null,
            lastChapter  = "",
            isFinished = false,
            listLinkedId = emptyList(),
            contentRating = String.empty
        )
    }
}

