package com.arakene.musicplayer.ui.ui_parameter

import android.os.Parcelable
import com.arakene.musicplayer.network.response.Image
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class PlaylistParameter(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val images: List<Image> = emptyList()
) : Parcelable