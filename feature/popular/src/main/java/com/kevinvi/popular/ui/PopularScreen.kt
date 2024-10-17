package com.kevinvi.popular.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.kevinvi.data.mapper.PopularItemMapper
import com.kevinvi.ui.Dimens
import com.kevinvi.ui.Loader

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PopularScreen(
    navController: NavHostController = rememberNavController(),
) {
    val viewModel: PopularViewModel = hiltViewModel()
    val lazyPagingItems = viewModel.popularState.collectAsLazyPagingItems()
    val state = lazyPagingItems.loadState


    when (lazyPagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            Loader(true)
        }

        is LoadState.Error -> {
            Text(
                text = "An error occurred. Please try again.",
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }

        else -> {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 140.dp),
                reverseLayout = false,
                horizontalArrangement = Arrangement.End,
                contentPadding = PaddingValues(
                    start = Dimens.BIG_SPACING,
                    end = Dimens.BIG_SPACING,
                    bottom = Dimens.BIG_SPACING,
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
                content = {
                    items(count = lazyPagingItems.itemCount) { index ->
                        lazyPagingItems[index]?.let { popular ->

                            val item = PopularItemMapper.mapToPopular(popular)
                            PopularItem(
                                item = item,
                            )

                        }
                    }
                    when (state.append) {
                        is LoadState.Loading -> {
                            item(span = {
                                GridItemSpan(maxLineSpan)
                            }) {
                                Loader(true)
                            }
                        }

                        is LoadState.Error -> {
                            item(span = {
                                GridItemSpan(maxLineSpan)
                            }) {
                                Text("error")
                            }
                        }

                        else -> Unit
                    }
                },
            )
        }


    }
}



