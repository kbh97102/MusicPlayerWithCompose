package com.arakene.musicplayer.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface YoutubeRepository {

    suspend fun search(searchTarget: String): Response<String>

}

class YoutubeRepositoryImpl(
    private val api: YoutubeApi
) : YoutubeRepository {
    override suspend fun search(searchTarget: String): Response<String> {
        return api.search(q = searchTarget)
    }
}

// FOR TEST
object Client {
    val client = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/youtube/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }
            ).build())
        .build()
        .create(YoutubeApi::class.java)
}