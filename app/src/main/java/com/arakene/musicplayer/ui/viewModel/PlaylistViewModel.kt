package com.arakene.musicplayer.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arakene.musicplayer.actions.PlaylistAction
import com.arakene.musicplayer.network.TestClient
import com.arakene.musicplayer.states.PlaylistState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlaylistViewModel : ViewModel() {

    private val _state = MutableStateFlow(PlaylistState())
    val state: StateFlow<PlaylistState> get() = _state

    fun handleAction(action: PlaylistAction) {
        reduce(_state.value, action)
    }

    private fun reduce(state: PlaylistState, action: PlaylistAction) {
        when (action) {
            is PlaylistAction.GetData -> {
                viewModelScope.launch {
                    TestClient.client.getPlaylistDetail(action.id).body()?.let {
                        Log.e(">>>>", "BODY? ${it.tracks.items.size}")
                        _state.value = state.copy(data = it)
                    }
                }
            }

            is PlaylistAction.AddToMyPlayList -> {

            }
            is PlaylistAction.CreatePlaylist -> {

            }
            PlaylistAction.GetPlaylists -> {

            }
        }
    }
}