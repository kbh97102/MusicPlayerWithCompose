package com.arakene.musicplayer.network

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TestClient {

    lateinit var client: SpotifyAPI

    fun init(dataStore: DataStore<Preferences>) {
        client = Retrofit.Builder()
            .baseUrl("https://api.spotify.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            this.level = HttpLoggingInterceptor.Level.BODY
                        })
                    .addInterceptor(TokenInterceptor(dataStore))
                    .build())
            .build()
            .create(SpotifyAPI::class.java)
    }

}