package com.arakene.musicplayer.states

import com.arakene.musicplayer.network.response.Playlist
import com.arakene.musicplayer.network.response.PlaylistDto

data class PlaylistState(
    val data: PlaylistDto
){
    constructor(): this(data = PlaylistDto())
}