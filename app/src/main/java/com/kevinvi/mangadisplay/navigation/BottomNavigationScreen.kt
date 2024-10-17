package com.kevinvi.mangadisplay.navigation

import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.BugReport
import androidx.compose.material.icons.rounded.Home
import com.kevinvi.popular.navigation.FEATURE1_ROUTE
import com.kevinvi.popular.navigation.POPULAR_ROUTE
import fr.npo.remotecontrolbluetooth.core.ui.Icon.ImageVectorIcon
import fr.npo.remotecontrolbluetooth.feature.feature1.R.string.feature_feature1_label
import fr.npo.remotecontrolbluetooth.feature.feature2.R.string.feature_feature2_label
import fr.npo.remotecontrolbluetooth.feature.feature2.ui.navigation.FEATURE2_ROUTE

sealed class BottomNavigationScreen(
    val route: String,
    @StringRes val nameResourceId: Int,
    val selectedIcon: Icon,
    val unselectedIcon: Icon = selectedIcon,
) {
    data object Popular : BottomNavigationScreen(
        route = POPULAR_ROUTE,
        nameResourceId = feature_feature1_label,
        selectedIcon = ImageVectorIcon(Rounded.Home),
    )

    data object Search : BottomNavigationScreen(
        route = FEATURE2_ROUTE,
        nameResourceId = feature_feature2_label,
        selectedIcon = ImageVectorIcon(Rounded.BugReport),
    )

}
