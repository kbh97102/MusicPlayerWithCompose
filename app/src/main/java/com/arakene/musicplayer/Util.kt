package com.arakene.musicplayer

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.compose.runtime.compositionLocalOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.arakene.musicplayer.network.response.Playlist
import com.arakene.musicplayer.ui.ui_parameter.PlaylistParameter
import kotlinx.serialization.json.Json
import java.util.Base64

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


val NavigatorCompositionLocal = compositionLocalOf<NavHostController> { error("NO Navigator") }


val PlaylistType = object : NavType<PlaylistParameter>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): PlaylistParameter? {
        return bundle.getString(key)?.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): PlaylistParameter {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: PlaylistParameter) {
        bundle.putString(key, Json.encodeToString(PlaylistParameter.serializer(), value))
    }

    override fun serializeAsValue(value: PlaylistParameter): String {
        return Json.encodeToString(PlaylistParameter.serializer(), value)
    }
}


fun String.convertToBase64() = Base64.getEncoder().encodeToString(this.toByteArray())
fun String.decodeToBase64() = Base64.getDecoder().decode(this.toByteArray()).toString()