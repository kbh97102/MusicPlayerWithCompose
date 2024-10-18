package com.arakene.musicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arakene.musicplayer.network.TestClient
import com.arakene.musicplayer.ui.Main

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TestClient.init(this.dataStore)

        setContent {
            Main()
        }
    }
}
