package com.arakene.musicplayer.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationResponse


@Composable
fun SpotifyTest() {

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        val response = AuthorizationClient.getResponse(it.resultCode, it.data)
        when (response.type) {
            AuthorizationResponse.Type.TOKEN -> {
                Log.d(">>>>", "TOKEN $response")
                response.accessToken
            }

            AuthorizationResponse.Type.ERROR -> {
                Log.e(">>>>", "ERROR ${response.type} ${response.error}")
            }

            else -> {
                Log.d(">>>>", "else $response")
            }
        }

    }

    Column {
        Button(
            onClick = {
                SpotifyUtil.connect(context)
            }
        ) {
            Text(text = "init")
        }

        Button(
            onClick = {
                SpotifyUtil.requestAuth(context.findActivity(), launcher = {
                    launcher.launch(it)
                })
            }
        ) {
            Text(text = "auth")

        }

        Button(
            onClick = {
                SpotifyUtil.search()
            }
        ) { }
    }
}

internal fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Permissions should be called in the context of an Activity")
}