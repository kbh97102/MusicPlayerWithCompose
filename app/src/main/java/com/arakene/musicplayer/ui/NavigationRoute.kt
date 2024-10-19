package com.arakene.musicplayer.ui

import kotlinx.serialization.Serializable


/**
 * safety args 를 사용해서 Playlist 로 이동 시 데이터 클래스로 파라미터를 넘기고 싶었으나
 * url이 제거된 데이터클래스도 커스텀 클래스 사용 시 destination을 못찾음
 * typeMap과도 관련없는게 navigate() 함수 자체에서 못찾아서 발생하는 에러
 *
 * UiState를 통해 데이터 전달하도록 함
 */
@Serializable
sealed class NavigationRoute {
    @Serializable
    data object Main


    @Serializable
    data object Playlist

    @Serializable
    data object MyPlaylist
}

