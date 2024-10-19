package com.arakene.musicplayer.actions

sealed class PlaylistAction {

    data class GetData(val id: String) : PlaylistAction()

    data class AddToMyPlayList(val uri: String) : PlaylistAction()

    data object GetPlaylists : PlaylistAction()

    data class CreatePlaylist(val userId: String, val playlistName: String) : PlaylistAction()

}