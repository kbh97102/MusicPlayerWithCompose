package com.arakene.musicplayer.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arakene.musicplayer.actions.MainAction
import com.arakene.musicplayer.network.TestClient
import com.arakene.musicplayer.network.response.Playlist
import com.arakene.musicplayer.states.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _mainState = MutableStateFlow(MainState())
    val mainState: StateFlow<MainState> get() = _mainState

    fun handleAction(action: MainAction) {
        reduce(_mainState.value, action)
    }

    fun testMethod(playlist: Playlist) {
        _mainState.value = _mainState.value.copy(selectedPlayList = playlist)
    }


    private fun reduce(state: MainState, action: MainAction) {
        when (action) {
            MainAction.PullToRefresh -> {
                viewModelScope.launch {
                    TestClient.client.search("이세계아이돌", type = "playlist").body()?.let {
                        _mainState.value = state.copy(mainData = listOf(it))
                        Log.e(">>>>", "refresh")
                    }
                }
            }

            MainAction.ScrollToTop -> {
                _mainState.value = state.copy(scrollToTop = true)
                Log.e(">>>>", "scrollToTop")
            }

            is MainAction.SelectPlayList -> {
                // TODO: 다음화면으로 이동하는걸 여기서 처리한다고? 이게 맞나?
            }
        }
    }
}