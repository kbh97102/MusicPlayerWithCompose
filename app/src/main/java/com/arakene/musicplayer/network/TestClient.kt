package com.arakene.musicplayer.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TestClient {

    val client = Retrofit.Builder()
        .baseUrl("https://api.spotify.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }
            ).build())
        .build()
        .create(SpotifyAPI::class.java)

}