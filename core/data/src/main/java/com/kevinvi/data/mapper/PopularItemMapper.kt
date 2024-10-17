package com.kevinvi.data.mapper

import com.kevinvi.common.extensions.empty
import com.kevinvi.data.model.PopularData
import com.kevinvi.data.model.PopularDescription
import com.kevinvi.data.model.PopularItemUI
import com.kevinvi.data.model.PopularRelationships

object PopularItemMapper {


    fun mapToPopular(popularItem: PopularData) = PopularItemUI(
        id = popularItem.id,
        title = getDescription(popularItem.attributes?.title),
        altTitles = popularItem.attributes?.altTitles?.toString(),
        description = getDescription(popularItem.attributes?.description),
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

    private fun getDescription(desc: PopularDescription?) =
        if (!desc?.fr.isNullOrEmpty()) {
            desc.fr
        } else if (!desc?.en.isNullOrEmpty()) {
            desc.en
        } else {
            String.empty
        }


    private fun getImage(id: String, coverArt: String) =
        "https://uploads.mangadex.org/covers/$id/$coverArt"
}