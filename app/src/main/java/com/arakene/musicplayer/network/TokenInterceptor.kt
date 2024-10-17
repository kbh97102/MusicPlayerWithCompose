package com.arakene.musicplayer.network

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val dataStore: DataStore<Preferences>) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return runBlocking {
            val token = dataStore.data.first()[stringPreferencesKey("token")]



            Log.d(">>>>", "Token $token")

            val request = chain.request()
                .newBuilder()
                .header("Authorization", "Bearer $token")
                .build()

            Log.e(">>>>", "URL ${request.url}")

            chain.proceed(request)
        }
    }
}