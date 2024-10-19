package com.arakene.musicplayer.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class GetUserPlaylistDTO(
    @SerializedName("href") val href: String = "",
    @SerializedName("limit") val limit: Int = -1,
    @SerializedName("next") val next: String? = null,
    @SerializedName("offset") val offset: Int = -1,
    @SerializedName("previous") val previous: String? = null,
    @SerializedName("total") val total: Int = -1,
    @SerializedName("items") val items: List<Playlist> = emptyList()
) : Parcelable
