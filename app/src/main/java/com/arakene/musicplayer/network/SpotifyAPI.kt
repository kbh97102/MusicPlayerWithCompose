package com.arakene.musicplayer.network

import com.arakene.musicplayer.network.response.PlaylistDto
import com.arakene.musicplayer.network.response.SearchResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyAPI {

    @GET("/v1/users/{user_id}/playlists")
    suspend fun getUserPlaylist(
        @Path("user_id") userId: String
    )

    @GET("/v1/search")
    suspend fun search(
        @Query("q") query: String,
        @Query("type") type: String,
        @Query("market") market: String = "KR"
    ): Response<SearchResponse>

    @GET("/v1/playlists/{playlist_id}")
    suspend fun getPlaylistDetail(
        @Path("playlist_id") playlistId: String,
        @Query("market") market: String = "KR",
    ): Response<PlaylistDto>

}