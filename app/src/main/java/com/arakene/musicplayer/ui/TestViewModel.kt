package com.arakene.musicplayer.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arakene.musicplayer.network.Client
import com.arakene.musicplayer.network.YoutubeRepositoryImpl
import kotlinx.coroutines.launch

class TestViewModel: ViewModel() {

    // FOR TEST
    private val repo = YoutubeRepositoryImpl(Client.client)


    fun search(target: String) {
        viewModelScope.launch {
            repo.search(searchTarget = target).also {
                Log.d(">>>>", "result ${it.body()}")
            }
        }
    }
}