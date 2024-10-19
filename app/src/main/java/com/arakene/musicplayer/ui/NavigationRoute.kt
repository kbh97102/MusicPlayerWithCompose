package com.arakene.musicplayer.ui

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.navigation.Navigator
import com.arakene.musicplayer.network.response.Playlist
import com.arakene.musicplayer.ui.ui_parameter.PlaylistParameter
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


/**
 * safety args 를 사용해서 Playlist 로 이동 시 데이터 클래스로 파라미터를 넘기고 싶었으나
 * url이 제거된 데이터클래스도 커스텀 클래스 사용 시 destination을 못찾음
 * typeMap과도 관련없는게 navigate() 함수 자체에서 못찾아서 발생하는 에러
 *
 * UiState를 통해 데이터 전달하도록 함
 */
@Keep
@Parcelize
@Serializable
sealed interface NavigationRoute : Parcelable {

    @Keep
    @Parcelize
    @Serializable
    data object Main: NavigationRoute

    @Keep
    @Parcelize
    @Serializable
    data class PlaylistRoute(val playlist: PlaylistParameter): NavigationRoute

    @Keep
    @Parcelize
    @Serializable
    data object MyPlaylist: NavigationRoute
}

