package com.arakene.musicplayer.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.arakene.musicplayer.actions.PlaylistAction
import com.arakene.musicplayer.network.response.Playlist
import com.arakene.musicplayer.network.response.TrackDto
import com.arakene.musicplayer.ui.viewModel.PlaylistViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
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

    var expanded by remember {
        mutableStateOf(false)
    }

    var dropdownOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }

    var itemHeight by remember {
        mutableStateOf(0.dp)
    }

    val density = LocalDensity.current

    var displayDialog by remember {
        mutableStateOf(false)
    }

    if (displayDialog) {
        SelectPlayListDialog(
            setVisible = {
                displayDialog = it
            },
            playlists = emptyList(),
            handleAction = {
                viewModel.handleAction(it)
            }
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .background(color = Color.Black)
            .onSizeChanged {
                itemHeight = with(density) { it.height.toDp() }
            },
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
                MusicItem(
                    it.track, addToPlaylist = {

                    }, modifier = Modifier

                )
            }
        }


    }

}

@Composable
fun SelectPlayListDialog(
    setVisible: (Boolean) -> Unit,
    playlists: List<Playlist>,
    handleAction: (PlaylistAction) -> Unit
) {
    Dialog(onDismissRequest = {
        setVisible(false)
    }) {

        Column {
            playlists.forEach {
                UserPlaylistItem(it, modifier = Modifier.clickable {
                    handleAction(PlaylistAction.AddToMyPlayList(it.uri))
//                    selectPlaylist(it)
                })
            }

            Text(text = "플리 추가하기", modifier = Modifier.clickable {

//                createPlaylist()
            })
        }

    }
}

@Composable
fun MusicItem(
    data: TrackDto,
    addToPlaylist: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        //이름
        Text(text = data.name, color = Color.White, modifier = Modifier.padding(end = 10.dp))

        // 가수?
        data.artists.forEach {
            Text(text = it.name, color = Color.White)
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(text = "+", color = Color.White, modifier = Modifier
            .padding(end = 10.dp)
            .clickable {
                addToPlaylist(data.uri)
            })

    }
}