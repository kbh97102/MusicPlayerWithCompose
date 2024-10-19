package com.arakene.musicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.arakene.musicplayer.network.TestClient
import com.arakene.musicplayer.network.response.Playlist
import com.arakene.musicplayer.ui.Main
import com.arakene.musicplayer.ui.NavigationRoute
import com.arakene.musicplayer.ui.PlayListDetailView
import com.arakene.musicplayer.ui.UserPlayListView
import com.arakene.musicplayer.ui.ui_parameter.PlaylistParameter
import com.arakene.musicplayer.ui.viewModel.MainViewModel
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TestClient.init(this.dataStore)

        setContent {
            val controller = rememberNavController()

            val testViewModel = remember {
                MainViewModel()
            }

            val uiState by testViewModel.mainState.collectAsState()

            CompositionLocalProvider(NavigatorCompositionLocal provides controller) {
                NavHost(
                    navController = controller,
                    startDestination = NavigationRoute.Main
                ) {
                    composable<NavigationRoute.PlaylistRoute>(
                        typeMap = mapOf(typeOf<PlaylistParameter>() to PlaylistType)
                    ) {
                        val data = it.toRoute<PlaylistParameter>()

                        uiState.selectedPlayList?.let {
                            PlayListDetailView(it)
                        }

                    }

                    composable<NavigationRoute.Main> {
                        Main(
                            viewModel = testViewModel,
                            moveTest = {
                                controller.navigate(NavigationRoute.PlaylistRoute(it))
                            }
                        )
                    }


                    composable<NavigationRoute.MyPlaylist> {
                        UserPlayListView()
                    }
                }

            }
        }
    }
}
