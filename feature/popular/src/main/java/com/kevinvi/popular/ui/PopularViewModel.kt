package com.kevinvi.popular.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kevinvi.popular.data.model.PopularData
import com.kevinvi.popular.data.repository.PopularRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    val popularRepository: PopularRepository,
) : ViewModel() {
    init {
        populare()
    }

    private val _popularState: MutableStateFlow<PagingData<PopularData>> =
        MutableStateFlow(value = PagingData.Companion.empty())
    val popularState: MutableStateFlow<PagingData<PopularData>> get() = _popularState

    fun populare() {
        viewModelScope.launch(Dispatchers.IO) {

            popularRepository.getPopular().distinctUntilChanged().cachedIn(viewModelScope).collect {
                _popularState.value = it
            }


        }
    }


}

