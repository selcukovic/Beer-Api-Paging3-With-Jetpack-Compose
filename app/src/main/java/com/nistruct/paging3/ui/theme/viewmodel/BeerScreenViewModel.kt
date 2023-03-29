package com.nistruct.paging3.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.nistruct.paging3.data.local.BeerEntity
import com.nistruct.paging3.data.mappers.toBeer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BeerScreenViewModel @Inject constructor(pager : Pager<Int,BeerEntity>): ViewModel() {
    val beerPagingFlow = pager.flow.map {
        pagingData->
        pagingData.map { it.toBeer() }
    }.cachedIn(viewModelScope)
}