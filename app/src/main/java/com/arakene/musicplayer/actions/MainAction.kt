package com.arakene.musicplayer.actions

interface Action

sealed class MainAction: Action {

    data class SelectPlayList(val playListId: String) : MainAction()
    data object ScrollToTop: MainAction()
    data object PullToRefresh: MainAction()

}