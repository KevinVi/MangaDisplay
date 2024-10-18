package com.kevinvi.search.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kevinvi.search.navigation.navigateToDetails
import com.kevinvi.ui.Dimens
import com.kevinvi.ui.components.Loader

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavHostController = rememberNavController(),
) {

    val viewModel: SearchViewModel = hiltViewModel()
    var text by rememberSaveable { mutableStateOf("") }
    var searchLaunched by rememberSaveable { mutableStateOf(false) }
    val search by viewModel.stateData.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var expanded by rememberSaveable { mutableStateOf(false) }

    Scaffold {
        Box(
            Modifier
                .fillMaxSize()
                .semantics { isTraversalGroup = true }) {
            SearchBar(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .semantics { traversalIndex = 0f },
                inputField = {
                    SearchBarDefaults.InputField(
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        placeholder = { Text("Rechercher un manga ou un anime ici !") },
                        query = text,
                        onQueryChange = {
                            text = it
                        },
                        onSearch = {
                            expanded = false
                            searchLaunched = true
                            viewModel.search(text)
                            focusManager.clearFocus()
                            keyboardController?.hide()
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon"
                            )
                        },
                        trailingIcon = {
                            Icon(
                                modifier = Modifier.clickable {
                                    if (text.isNotEmpty()) {
                                        text = ""
                                    }
                                },
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close item"
                            )
                        },
                    )
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
            ) {}

            if (searchLaunched) {
                if (search.isMangaLoading && searchLaunched) {
                    Loader(true)
                } else {
                    Loader(false)
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
                            items(search.list) { itemUi ->
                                MangaSearchResult(
                                    itemUi,
                                    onItemClick = {
                                        navController.navigateToDetails(itemUi)
                                    }
                                )
                            }
                        }
                    )
                }

            } else {
                Text(
                    text = "Recherche ici vos mangas et animés préférés  ",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 30.dp, end = 30.dp)
                        .wrapContentHeight()
                )
            }
        }

    }
}
