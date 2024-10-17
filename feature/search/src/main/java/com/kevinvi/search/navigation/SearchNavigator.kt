package com.kevinvi.search.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kevinvi.common.NavigationUtils.URL_ENCODING
import com.kevinvi.data.model.AssetParamTypeScan
import com.kevinvi.data.model.PopularItemUI
import com.kevinvi.search.ui.SearchScreen
import com.kevinvi.ui.DetailScreen
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.net.URLEncoder

const val SEARCH_ROUTE = "search"
const val SEARCH_ROUTE_DETAIL = "search_detail"

fun NavController.navigateToSearch(navOptions: NavOptions) {
    navigate(SEARCH_ROUTE, navOptions)
}

fun NavController.navigateToDetails(item: PopularItemUI) {
    val json = Json.encodeToJsonElement(item)

    Log.d("TAG", "navigateToDetails: $json")
    navigate(
        "$SEARCH_ROUTE_DETAIL/${URLEncoder.encode(json.toString(), URL_ENCODING)}",
    )
}

fun NavGraphBuilder.addSearchRoute(navController: NavHostController) {
    composable(SEARCH_ROUTE) { SearchScreen(navController) }
    composable(
        "$SEARCH_ROUTE_DETAIL/{data}",
        arguments = listOf(
            navArgument("data") {
                type = AssetParamTypeScan()
            },
        ),
    ) {
        it.arguments?.getParcelable<PopularItemUI>("data")?.let { item ->
            DetailScreen(
                item,
                onBackClick = {
                    navController.navigateUp()
                },
            )
        }
    }
}
