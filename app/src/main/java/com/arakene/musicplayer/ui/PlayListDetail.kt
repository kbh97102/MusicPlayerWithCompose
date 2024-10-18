package com.arakene.musicplayer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun PlayListDetail(
    data: Playlist,
    modifier: Modifier = Modifier,
    viewModel: PlaylistViewModel = PlaylistViewModel()
) {

    val playlistData by viewModel.state.collectAsState()

    val playlist by remember(playlistData) {
        mutableStateOf(playlistData.data.tracks)
    }

    LaunchedEffect(data) {
        viewModel.handleAction(PlaylistAction.GetData(data.id))
    }

    Column(
        modifier = Modifier
            .then(modifier)
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            model = data.images.firstOrNull()?.url,
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )

        Text(data.name)

        Text(data.description)

        Row {
            Text("Button Row")
        }

        // 음악 리스트
        LazyColumn {
            items(playlist.items) {
                MusicItem(it.track)
            }
        }

    }

}

@Composable
fun MusicItem(
    data: TrackDto
) {
    Row {

        //이름
        Text(text = data.name)

        // 가수?
        data.artists.forEach {
            Text(text = it.name)
        }

    }
}