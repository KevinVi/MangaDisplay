package com.kevinvi.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import com.kevinvi.common.extensions.takeIfNotNullOrBlank
import com.kevinvi.popular.ui.PopularItemUI
import kotlin.let
import kotlin.to

@androidx.compose.runtime.Composable
fun MangaSearchResult(
    item: PopularItemUI,

    ) {

    Card(
        elevation = cardElevation(),
        modifier = Modifier
            .padding(10.dp)
            .height(225.dp)
            .width(150.dp)
    )
    {


        Column {
            var state by remember<MutableState<Boolean>> {
                androidx.compose.runtime.mutableStateOf(false)
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                item.image.takeIfNotNullOrBlank()?.let {
                    coil.compose.AsyncImage(
                        model = it,
                        contentDescription = null,
                        contentScale = androidx.compose.ui.layout.ContentScale.Companion.Crop,
                        modifier = Modifier.aspectRatio(2f / 3f),
                        onSuccess = {
                            state = true
                        }

                    )
                }

                if (state) {
                    item.title.takeIfNotNullOrBlank()?.let {
                        Text(
                            text = it,
                            Modifier
                                .fillMaxWidth()
                                .align(alignment = BottomStart)
                                .background(
                                    brush = linearGradient(
                                        0.0f to Transparent,
                                        1.0f to Color.Black.copy(0.6f),
                                        start = Offset(0.0f, 10.0f),
                                        end = androidx.compose.ui.geometry.Offset(0.0f, 100.0f)
                                    )
                                )
                                .padding(10.dp),
                            overflow = Ellipsis,
                            maxLines = 3,
                            color = White,

                            )
                    }
                }
            }

        }
    }

}

@androidx.compose.ui.tooling.preview.Preview
@androidx.compose.runtime.Composable
fun ScanItemComposable() {

    MangaSearchResult(
        PopularItemUI(
            id = "10",
            title = "One piece",
            altTitles = "",
            description = "un mec qui a un équipage et qui veut devenir roi des pirates",
            createdAt = "date 1 ",
            updatedAt = "data 2 ",
            image = "https://uploads.mangadex.org/covers/68112dc1-2b80-4f20-beb8-2f2a8716a430/c3f43d5a-83c4-44bd-a117-b247019329b2.jpg",
            lastChapter = null,
            isFinished = false,
        ),
    )
}