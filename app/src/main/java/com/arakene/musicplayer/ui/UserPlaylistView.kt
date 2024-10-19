package com.arakene.musicplayer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arakene.musicplayer.network.response.Playlist
import com.arakene.musicplayer.ui.viewModel.UserPlaylistViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun UserPlayListView(
    modifier: Modifier = Modifier,
    viewModel: UserPlaylistViewModel = remember {
        UserPlaylistViewModel()
    }
) {
    val state by viewModel.state.collectAsState()

    val playlists by remember(state.playlistDto.items) {
        mutableStateOf(state.playlistDto.items)
    }

    Column(
        modifier = Modifier
            .then(modifier)
            .fillMaxSize()
            .background(color = Color.Black)
    ) {

        Text("내 플레이 리스트", color = Color.White)

        if (playlists.isEmpty()) {
            Text("플레이 리스트가 비어있어요", color = Color.White)
        } else {
            playlists.forEach {
                UserPlaylistItem(it)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserPlaylistItem(
    data: Playlist,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            model = data.images.firstOrNull()?.url,
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )

        Text(text = data.name, color = Color.White, modifier = Modifier.padding(start = 10.dp))

        Text(
            text = data.tracks.total.toString(),
            color = Color.White,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}