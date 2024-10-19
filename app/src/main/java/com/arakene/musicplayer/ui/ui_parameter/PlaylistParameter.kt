package com.arakene.musicplayer.ui.ui_parameter

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Keep
@Parcelize
@Serializable
data class PlaylistParameter(
    val id: String = "",
    val name: String = "",
    val description: String = "",
) : Parcelable