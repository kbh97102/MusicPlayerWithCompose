package com.arakene.musicplayer.ui

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Stable
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.YouTubeScopes

@Stable
class Util {

    private val JSON_FACTORY: JsonFactory = GsonFactory.getDefaultInstance()

    private var mCredential: GoogleAccountCredential? = null

    lateinit var youtube: YouTube
        private set


    fun getAccountIntent() = mCredential?.newChooseAccountIntent()

    fun setName(name: String) {
        mCredential?.setSelectedAccountName(name)
    }

    fun setCredential(context: Context) {
        if (mCredential != null) {
            return
        }
        mCredential = GoogleAccountCredential.usingOAuth2(
            context,
            listOf(YouTubeScopes.YOUTUBE_READONLY)
        ).setBackOff(ExponentialBackOff())

//        setService(context)
    }

    fun setService(context: Context) {
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()

        Log.e(">>>>", "여기 오긴하니? ${mCredential == null}")

        Log.d(">>>>", "Credential ${mCredential?.selectedAccountName}")

        youtube = YouTube.Builder(httpTransport, JSON_FACTORY, mCredential)
            .setApplicationName(context.applicationInfo.name)
            .build()
    }


}
