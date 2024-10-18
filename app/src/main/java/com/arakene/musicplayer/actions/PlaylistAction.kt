package com.arakene.musicplayer.actions

sealed class PlaylistAction {

    data class GetData(val id: String): PlaylistAction()


}