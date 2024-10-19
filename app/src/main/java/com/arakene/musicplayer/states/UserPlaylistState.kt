package com.arakene.musicplayer.states

import com.arakene.musicplayer.network.response.GetUserPlaylistDTO

data class UserPlaylistState(
    val playlistDto: GetUserPlaylistDTO
){
    constructor(): this(GetUserPlaylistDTO())
}
