package com.kevinvi.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevinvi.popular.mapper.PopularItemMapper
import com.kevinvi.popular.ui.PopularItemUI
import com.kevinvi.search.data.repository.MangaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val mangaRepository: MangaRepository,
) : ViewModel() {


    private var _stateData = MutableStateFlow(MangaListUiState())

    val stateData: StateFlow<MangaListUiState>
        get() = _stateData

    fun search(data: String) {
        _stateData.update { it.copy(isMangaLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            mangaRepository.getMangaByName(data).let {
                val result = it.data.map { PopularItemMapper.mapToPopular(it) }
                _stateData.update { it.copy(list = result) }
            }
            _stateData.update { it.copy(isMangaLoading = false) }


        }
    }
}

data class MangaListUiState(
    val list: List<PopularItemUI> = emptyList(),
    val isMangaLoading: Boolean = true,
)