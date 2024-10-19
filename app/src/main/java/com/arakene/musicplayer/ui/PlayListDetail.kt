package com.arakene.musicplayer.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arakene.musicplayer.actions.PlaylistAction
import com.arakene.musicplayer.network.response.Playlist
import com.arakene.musicplayer.network.response.TrackDto
import com.arakene.musicplayer.ui.viewModel.PlaylistViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayListDetailView(
    data: Playlist,
    modifier: Modifier = Modifier,
    viewModel: PlaylistViewModel = remember { PlaylistViewModel() }
) {

    val playlistData by viewModel.state.collectAsState()

    val playlist by remember(playlistData) {
        mutableStateOf(playlistData.data.tracks)
    }

    LaunchedEffect(data) {
        viewModel.handleAction(PlaylistAction.GetData(data.id))
    }

    LaunchedEffect(playlist) {

        Log.e(">>>>", "LIST? ${playlist.items.size}")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            model = data.images.firstOrNull()?.url,
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )

        Text(data.name, color = Color.White)

        Text(data.description, color = Color.White)

        Row {
            Text("Button Row", color = Color.White)
        }

        // 음악 리스트
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(playlist.items) {
                MusicItem(it.track)
            }
        }

    }

}

@Composable
fun MusicItem(
    data: TrackDto,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.background(color = Color.DarkGray)) {

        //이름
        Text(text = data.name, color = Color.White, modifier = Modifier.padding(end = 10.dp))

        // 가수?
        data.artists.forEach {
            Text(text = it.name, color = Color.White)
        }

    }
}