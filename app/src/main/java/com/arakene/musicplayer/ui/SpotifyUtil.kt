package com.arakene.musicplayer.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Stable
import com.arakene.musicplayer.network.TestClient
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse


@Stable
object SpotifyUtil{

    private val clientId = "f1517bd71b8e4672ae4902452a2cb886"
    private val redirectUri = "https://arakene.musicplayer.com/android-redirect"
//    private val redirectUri = "arakenetest://callback"
    private var spotifyAppRemote: SpotifyAppRemote? = null


    private fun getConnectParam() = ConnectionParams.Builder(clientId)
        .setRedirectUri(redirectUri)
        .showAuthView(true)
        .build()


    fun connect(context: Context) {
        SpotifyAppRemote.connect(context, getConnectParam(), object : Connector.ConnectionListener {
            override fun onConnected(appRemote: SpotifyAppRemote) {
                spotifyAppRemote = appRemote

                // Now you can start interacting with App Remote
                Log.d(">>>>", "Connected")
            }

            override fun onFailure(throwable: Throwable) {
                throwable.printStackTrace()
                // Something went wrong when attempting to connect! Handle errors here
            }
        })
    }

    fun playMusic() {
        // Play a playlist
        spotifyAppRemote?.playerApi?.play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL")
    }

    fun requestAuth(activity: Activity, launcher: (Intent) -> Unit) {
        val builder =
            AuthorizationRequest.Builder(clientId, AuthorizationResponse.Type.TOKEN, redirectUri)

        builder.setScopes(arrayOf("streaming"))

        val request = builder.build()

//        AuthorizationClient.openLoginInBrowser(activity, request)

        launcher.invoke(AuthorizationClient.createLoginActivityIntent(activity, request))

    }

    suspend fun listSearchTest() {
        TestClient.client.search(query = "이세계아이돌", type = "playlist").also {
            Log.d(">>>>", "TEST ${it.body()}")
        }
    }

}