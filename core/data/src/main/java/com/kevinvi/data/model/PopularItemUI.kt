package com.kevinvi.data.model

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.kevinvi.common.NavigationUtils
import com.kevinvi.common.extensions.empty
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.URLDecoder

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
    val isFinished: Boolean,
) : Parcelable {

    companion object {
        val EMPTY = PopularItemUI(
            id = String.empty,
            title = null,
            altTitles = null,
            description = null,
            createdAt = null,
            updatedAt = null,
            image = null,
            lastChapter = "",
            isFinished = false,
        )
    }
}


class AssetParamTypeScan : NavType<PopularItemUI>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): PopularItemUI? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): PopularItemUI {
        return Json { ignoreUnknownKeys = true }.decodeFromString<PopularItemUI>(
            URLDecoder.decode(
                value,
                NavigationUtils.URL_ENCODING
            )
        )
    }

    override fun put(bundle: Bundle, key: String, value: PopularItemUI) {
        bundle.putParcelable(key, value)
    }
}

