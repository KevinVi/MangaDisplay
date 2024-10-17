package com.kevinvi.popular.mapper

import com.kevinvi.common.extensions.empty
import com.kevinvi.popular.data.model.PopularData
import com.kevinvi.popular.data.model.PopularRelationships
import com.kevinvi.popular.ui.PopularItemUI

object PopularItemMapper {

    fun mapToPopular(popularItem: PopularData) = PopularItemUI(
        id = popularItem.id,
        title = popularItem.attributes?.title?.toString(),
        altTitles = popularItem.attributes?.altTitles?.toString(),
        description = popularItem.attributes?.description?.toString(),
        createdAt = popularItem.attributes?.createdAt,
        updatedAt = popularItem.attributes?.updatedAt,
        image = getImage(popularItem.id, getCoverArt(popularItem.relationships)),
        isFinished = popularItem.attributes?.status == "completed",
    )

    private fun getCoverArt(relation: List<PopularRelationships>?): String {
        relation?.forEach {
            if (it.type == "cover_art") {
                return it.attributes?.fileName ?: String.empty
            }
        }
        return String.empty
    }


    private fun getImage(id: String, coverArt: String) =
        "https://uploads.mangadex.org/covers/$id/$coverArt"
}