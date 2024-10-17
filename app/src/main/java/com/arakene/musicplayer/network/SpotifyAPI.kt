package com.arakene.musicplayer.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyAPI {

    @GET("/users/{user_id}/playlists")
    fun getUserPlaylist(
        @Path("user_id") userId: String
    )

    @GET("/search")
    fun search(
        @Query("q") query: String,
        @Query("type") type: String,
        @Query("market") market: String = "KR"
    )

}