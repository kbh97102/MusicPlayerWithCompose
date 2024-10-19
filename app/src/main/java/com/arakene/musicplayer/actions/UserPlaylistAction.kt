package com.arakene.musicplayer.actions

sealed class UserPlaylistAction {

    data object GetUserPlaylist: UserPlaylistAction()

}