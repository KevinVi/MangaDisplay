package com.kevinvi.popular.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.loader.content.Loader
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.kevinvi.ui.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PopulareScreen(
    navController: NavHostController = rememberNavController(),
) {
    val viewModel: PopularViewModel = hiltViewModel()
    val lazyPagingItems = viewModel.popularState.collectAsLazyPagingItems()
    val state = lazyPagingItems.loadState
    val listState = rememberLazyListState()

    when {

        state.refresh is LoadState.Loading && lazyPagingItems.itemCount == 0 -> {
            //ShimmerHome()
        }

        state.refresh is LoadState.Loading && lazyPagingItems.itemCount == 0 -> {
            Box(
                modifier =
                Modifier.statusBarsPadding()
            ) {
               // Loader(true)
            }
        }

        state.refresh is LoadState.Error -> {
            TODO()
        }

        state.append is LoadState.Loading -> {
            Box(
                modifier =
                Modifier.statusBarsPadding()
            ) {
                //Loader(true)
            }
        }

        state.append is LoadState.Error -> {
            TODO()
        }

        else -> {

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(3),
                verticalItemSpacing = Dimens.NORMAL_SPACING,
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
            ) {
                items(count = lazyPagingItems.itemCount) { index ->
                    lazyPagingItems.get(index = index)?.let { popular ->



                        popular.attributes

                    }
                }
            }

        }
    }
}
