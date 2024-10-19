package com.arakene.musicplayer.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arakene.musicplayer.actions.UserPlaylistAction
import com.arakene.musicplayer.network.TestClient
import com.arakene.musicplayer.states.UserPlaylistState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserPlaylistViewModel : ViewModel() {

    private val _state = MutableStateFlow(UserPlaylistState())
    val state: StateFlow<UserPlaylistState> get() = _state

    fun handleAction(action: UserPlaylistAction) {
        reduce(_state.value, action)
    }

    private fun reduce(state: UserPlaylistState, action: UserPlaylistAction) {
        when (action) {
            is UserPlaylistAction.GetUserPlaylist -> {
                viewModelScope.launch {
                    TestClient.client.getMyPlaylist().body()?.let {
                        _state.value = state.copy(playlistDto = it)
                    }
                }
            }
        }
    }

}