package com.arakene.musicplayer.ui

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arakene.musicplayer.actions.MainAction
import com.arakene.musicplayer.dataStore
import com.arakene.musicplayer.ui.viewModel.MainViewModel
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Main(
    viewModel: MainViewModel = MainViewModel()
) {
    val mainState by viewModel.mainState.collectAsState()

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        val response = AuthorizationClient.getResponse(it.resultCode, it.data)
        when (response.type) {
            AuthorizationResponse.Type.TOKEN -> {
                Log.d(">>>>", "TOKEN $response")

                scope.launch {
                    context.dataStore.edit { settings ->
                        settings[stringPreferencesKey("token")] = response.accessToken
                    }
                }

                SpotifyUtil.connect(context)
            }

            AuthorizationResponse.Type.ERROR -> {
                Log.e(">>>>", "ERROR ${response.type} ${response.error}")
            }

            else -> {
                Log.d(">>>>", "else $response")
            }
        }

    }

    LaunchedEffect(Unit) {
        SpotifyUtil.requestAuth(context.findActivity(), launcher = {
            launcher.launch(it)
        })
    }

    LaunchedEffect(Unit) {

        scope.launch {
            delay(3000)
            viewModel.handleAction(MainAction.PullToRefresh)
        }

    }

    LaunchedEffect(mainState) {
        Log.e(">>>>", "무너가 변경점이 있니")
    }

    Column(modifier = Modifier.fillMaxSize()) {
        val data by remember(mainState.mainData) {
            mutableStateOf(mainState.mainData.firstOrNull()?.playlists)
        }

        LaunchedEffect(data) {
            Log.d(">>>>", "Data size ${data?.items?.size}")
        }

        if (!data?.items.isNullOrEmpty()) {
            val items = data!!.items

            LazyRow {

                items(items.size) {
                    val target = items[it]
                    Column(
                        modifier = Modifier
                            .size(128.dp)
                            .background(color = Color.Yellow, shape = RoundedCornerShape(8.dp))
                    ) {


                        Text("TITLE? ${target.name}")

                        Text("Owner? ${target.owner.displayName}")
                    }

                }


            }
        }


    }


}