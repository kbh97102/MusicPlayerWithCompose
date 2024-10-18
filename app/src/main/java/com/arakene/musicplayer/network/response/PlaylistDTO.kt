package com.arakene.musicplayer.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class PlaylistDto(
    @SerializedName("collaborative") val collaborative: Boolean = false,
    @SerializedName("description") val description: String = "",
    @SerializedName("external_urls") val externalUrls: ExternalUrlsDto = ExternalUrlsDto(),
    @SerializedName("followers") val followers: FollowersDto = FollowersDto(),
    @SerializedName("href") val href: String = "",
    @SerializedName("id") val id: String = "",
    @SerializedName("images") val images: List<ImageDto> = emptyList(),
    @SerializedName("name") val name: String = "",
    @SerializedName("owner") val owner: OwnerDto = OwnerDto(),
    @SerializedName("public") val isPublic: Boolean = false,
    @SerializedName("snapshot_id") val snapshotId: String = "",
    @SerializedName("tracks") val tracks: TracksDto = TracksDto(),
    @SerializedName("type") val type: String = "",
    @SerializedName("uri") val uri: String = ""
) : Parcelable

@Parcelize
data class ExternalUrlsDto(
    @SerializedName("spotify") val spotify: String = ""
) : Parcelable

@Parcelize
data class FollowersDto(
    @SerializedName("href") val href: String? = null,
    @SerializedName("total") val total: Int = 0
) : Parcelable

@Parcelize
data class ImageDto(
    @SerializedName("url") val url: String = "",
    @SerializedName("height") val height: Int? = null,
    @SerializedName("width") val width: Int? = null
) : Parcelable

@Parcelize
data class OwnerDto(
    @SerializedName("external_urls") val externalUrls: ExternalUrlsDto = ExternalUrlsDto(),
    @SerializedName("followers") val followers: FollowersDto = FollowersDto(),
    @SerializedName("href") val href: String = "",
    @SerializedName("id") val id: String = "",
    @SerializedName("type") val type: String = "user",
    @SerializedName("uri") val uri: String = "",
    @SerializedName("display_name") val displayName: String = ""
) : Parcelable

@Parcelize
data class TracksDto(
    @SerializedName("href") val href: String = "",
    @SerializedName("limit") val limit: Int = 0,
    @SerializedName("next") val next: String? = null,
    @SerializedName("offset") val offset: Int = 0,
    @SerializedName("previous") val previous: String? = null,
    @SerializedName("total") val total: Int = 0,
    @SerializedName("items") val items: List<TrackItemDto> = emptyList()
) : Parcelable

@Parcelize
data class TrackItemDto(
    @SerializedName("added_at") val addedAt: String = "",
    @SerializedName("added_by") val addedBy: AddedByDto = AddedByDto(),
    @SerializedName("is_local") val isLocal: Boolean = false,
    @SerializedName("track") val track: TrackDto = TrackDto()
) : Parcelable

@Parcelize
data class AddedByDto(
    @SerializedName("external_urls") val externalUrls: ExternalUrlsDto = ExternalUrlsDto(),
    @SerializedName("followers") val followers: FollowersDto = FollowersDto(),
    @SerializedName("href") val href: String = "",
    @SerializedName("id") val id: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("uri") val uri: String = ""
) : Parcelable

@Parcelize
data class TrackDto(
    @SerializedName("album") val album: AlbumDto = AlbumDto(),
    @SerializedName("artists") val artists: List<ArtistDto> = emptyList(),
    @SerializedName("available_markets") val availableMarkets: List<String> = emptyList(),
    @SerializedName("disc_number") val discNumber: Int = 0,
    @SerializedName("duration_ms") val durationMs: Int = 0,
    @SerializedName("explicit") val explicit: Boolean = false,
    @SerializedName("external_ids") val externalIds: ExternalIdsDto = ExternalIdsDto(),
    @SerializedName("external_urls") val externalUrls: ExternalUrlsDto = ExternalUrlsDto(),
    @SerializedName("href") val href: String = "",
    @SerializedName("id") val id: String = "",
    @SerializedName("is_playable") val isPlayable: Boolean = false,
    @SerializedName("linked_from") val linkedFrom: String? = null,
    @SerializedName("restrictions") val restrictions: RestrictionsDto? = null,
    @SerializedName("name") val name: String = "",
    @SerializedName("popularity") val popularity: Int = 0,
    @SerializedName("preview_url") val previewUrl: String? = null,
    @SerializedName("track_number") val trackNumber: Int = 0,
    @SerializedName("type") val type: String = "",
    @SerializedName("uri") val uri: String = "",
    @SerializedName("is_local") val isLocal: Boolean = false
) : Parcelable

@Parcelize
data class AlbumDto(
    @SerializedName("album_type") val albumType: String = "",
    @SerializedName("total_tracks") val totalTracks: Int = 0,
    @SerializedName("available_markets") val availableMarkets: List<String> = emptyList(),
    @SerializedName("external_urls") val externalUrls: ExternalUrlsDto = ExternalUrlsDto(),
    @SerializedName("href") val href: String = "",
    @SerializedName("id") val id: String = "",
    @SerializedName("images") val images: List<ImageDto> = emptyList(),
    @SerializedName("name") val name: String = "",
    @SerializedName("release_date") val releaseDate: String = "",
    @SerializedName("release_date_precision") val releaseDatePrecision: String = "",
    @SerializedName("restrictions") val restrictions: RestrictionsDto? = null,
    @SerializedName("type") val type: String = "",
    @SerializedName("uri") val uri: String = "",
    @SerializedName("artists") val artists: List<ArtistDto> = emptyList()
) : Parcelable

@Parcelize
data class ArtistDto(
    @SerializedName("external_urls") val externalUrls: ExternalUrlsDto = ExternalUrlsDto(),
    @SerializedName("href") val href: String = "",
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("uri") val uri: String = ""
) : Parcelable

@Parcelize
data class ExternalIdsDto(
    @SerializedName("isrc") val isrc: String? = null,
    @SerializedName("ean") val ean: String? = null,
    @SerializedName("upc") val upc: String? = null
) : Parcelable

@Parcelize
data class RestrictionsDto(
    @SerializedName("reason") val reason: String = ""
) : Parcelable
