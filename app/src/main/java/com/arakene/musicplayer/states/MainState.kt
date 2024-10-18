package com.arakene.musicplayer.states

import com.arakene.musicplayer.network.response.SearchResponse

interface UIState

data class MainState(
    var isLoading: Boolean,
    var mainData: List<SearchResponse>,
    var pullToRefresh: Boolean = false,
    var scrollToTop: Boolean = false
) : UIState {
    constructor() : this(isLoading = false, mainData = emptyList())
}