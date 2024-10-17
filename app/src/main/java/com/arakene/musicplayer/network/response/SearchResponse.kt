package com.arakene.musicplayer.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResponse(
    @SerializedName("tracks") val tracks: Tracks,
    @SerializedName("artists") val artists: Artist,
    @SerializedName("albums") val albums: Albums,
    @SerializedName("playlists") val playlists: Playlists,
    @SerializedName("shows") val shows: Shows,
    @SerializedName("episodes") val episodes: Episodes,
    @SerializedName("audiobooks") val audiobooks: Audiobooks
) : Parcelable

@Parcelize
data class Tracks(
    @SerializedName("href") val href: String,
    @SerializedName("limit") val limit: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("offset") val offset: Int,
    @SerializedName("previous") val previous: String?,
    @SerializedName("total") val total: Int,
    @SerializedName("items") val items: List<Track>
) : Parcelable

@Parcelize
data class Track(
    @SerializedName("album") val album: Album,
    @SerializedName("artists") val artists: List<Artist>,
    @SerializedName("available_markets") val availableMarkets: List<String>,
    @SerializedName("disc_number") val discNumber: Int,
    @SerializedName("duration_ms") val durationMs: Int,
    @SerializedName("explicit") val explicit: Boolean,
    @SerializedName("external_ids") val externalIds: ExternalIds,
    @SerializedName("external_urls") val externalUrls: ExternalUrls,
    @SerializedName("id") val id: String,
    @SerializedName("is_playable") val isPlayable: Boolean,
    @SerializedName("linked_from") val linkedFrom: String?,
    @SerializedName("restrictions") val restrictions: Restrictions,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("preview_url") val previewUrl: String?,
    @SerializedName("track_number") val trackNumber: Int,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String,
    @SerializedName("is_local") val isLocal: Boolean
) : Parcelable

@Parcelize
data class Album(
    @SerializedName("album_type") val albumType: String,
    @SerializedName("total_tracks") val totalTracks: Int,
    @SerializedName("available_markets") val availableMarkets: List<String>,
    @SerializedName("external_urls") val externalUrls: ExternalUrls,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<Image>,
    @SerializedName("name") val name: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("release_date_precision") val releaseDatePrecision: String,
    @SerializedName("restrictions") val restrictions: Restrictions,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String,
    @SerializedName("artists") val artists: List<Artist>
) : Parcelable

@Parcelize
data class Artist(
    @SerializedName("external_urls") val externalUrls: ExternalUrls,
    @SerializedName("followers") val followers: Followers,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<Image>,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
) : Parcelable

@Parcelize
data class ExternalUrls(
    @SerializedName("spotify") val spotify: String
) : Parcelable

@Parcelize
data class Followers(
    @SerializedName("href") val href: String?,
    @SerializedName("total") val total: Int
) : Parcelable

@Parcelize
data class Image(
    @SerializedName("url") val url: String,
    @SerializedName("height") val height: Int,
    @SerializedName("width") val width: Int
) : Parcelable

@Parcelize
data class Restrictions(
    @SerializedName("reason") val reason: String
) : Parcelable

@Parcelize
data class Albums(
    @SerializedName("href") val href: String,
    @SerializedName("limit") val limit: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("offset") val offset: Int,
    @SerializedName("previous") val previous: String?,
    @SerializedName("total") val total: Int,
    @SerializedName("items") val items: List<Album>
) : Parcelable

@Parcelize
data class Playlists(
    @SerializedName("href") val href: String,
    @SerializedName("limit") val limit: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("offset") val offset: Int,
    @SerializedName("previous") val previous: String?,
    @SerializedName("total") val total: Int,
    @SerializedName("items") val items: List<Playlist>
) : Parcelable

@Parcelize
data class Playlist(
    @SerializedName("collaborative") val collaborative: Boolean,
    @SerializedName("description") val description: String,
    @SerializedName("external_urls") val externalUrls: ExternalUrls,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<Image>,
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("public") val public: Boolean,
    @SerializedName("snapshot_id") val snapshotId: String,
    @SerializedName("tracks") val tracks: TracksInfo,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
) : Parcelable

@Parcelize
data class Owner(
    @SerializedName("external_urls") val externalUrls: ExternalUrls,
    @SerializedName("followers") val followers: Followers,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String,
    @SerializedName("display_name") val displayName: String
) : Parcelable

@Parcelize
data class TracksInfo(
    @SerializedName("href") val href: String,
    @SerializedName("total") val total: Int
) : Parcelable

@Parcelize
data class Shows(
    @SerializedName("href") val href: String,
    @SerializedName("limit") val limit: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("offset") val offset: Int,
    @SerializedName("previous") val previous: String?,
    @SerializedName("total") val total: Int,
    @SerializedName("items") val items: List<Show>
) : Parcelable

@Parcelize
data class Show(
    @SerializedName("available_markets") val availableMarkets: List<String>,
    @SerializedName("copyrights") val copyrights: List<Copyright>,
    @SerializedName("description") val description: String,
    @SerializedName("html_description") val htmlDescription: String,
    @SerializedName("explicit") val explicit: Boolean,
    @SerializedName("external_urls") val externalUrls: ExternalUrls,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<Image>,
    @SerializedName("is_externally_hosted") val isExternallyHosted: Boolean,
    @SerializedName("languages") val languages: List<String>,
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("name") val name: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String,
    @SerializedName("total_episodes") val totalEpisodes: Int
) : Parcelable

@Parcelize
data class Copyright(
    @SerializedName("text") val text: String,
    @SerializedName("type") val type: String
) : Parcelable

@Parcelize
data class Episodes(
    @SerializedName("href") val href: String,
    @SerializedName("limit") val limit: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("offset") val offset: Int,
    @SerializedName("previous") val previous: String?,
    @SerializedName("total") val total: Int,
    @SerializedName("items") val items: List<Episode>
) : Parcelable

@Parcelize
data class Episode(
    @SerializedName("audio_preview_url") val audioPreviewUrl: String?,
    @SerializedName("description") val description: String,
    @SerializedName("html_description") val htmlDescription: String,
    @SerializedName("duration_ms") val durationMs: Int,
    @SerializedName("explicit") val explicit: Boolean,
    @SerializedName("external_urls") val externalUrls: ExternalUrls,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<Image>,
    @SerializedName("is_externally_hosted") val isExternallyHosted: Boolean,
    @SerializedName("is_playable") val isPlayable: Boolean,
    @SerializedName("language") val language: String,
    @SerializedName("languages") val languages: List<String>,
    @SerializedName("name") val name: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("release_date_precision") val releaseDatePrecision: String,
    @SerializedName("resume_point") val resumePoint: ResumePoint,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String,
    @SerializedName("restrictions") val restrictions: Restrictions
) : Parcelable

@Parcelize
data class ResumePoint(
    @SerializedName("fully_played") val fullyPlayed: Boolean,
    @SerializedName("resume_position_ms") val resumePositionMs: Int
) : Parcelable

@Parcelize
data class Audiobooks(
    @SerializedName("href") val href: String,
    @SerializedName("limit") val limit: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("offset") val offset: Int,
    @SerializedName("previous") val previous: String?,
    @SerializedName("total") val total: Int,
    @SerializedName("items") val items: List<Audiobook>
) : Parcelable

@Parcelize
data class Audiobook(
    @SerializedName("authors") val authors: List<Author>,
    @SerializedName("available_markets") val availableMarkets: List<String>,
    @SerializedName("copyrights") val copyrights: List<Copyright>,
    @SerializedName("description") val description: String,
    @SerializedName("html_description") val htmlDescription: String,
    @SerializedName("edition") val edition: String,
    @SerializedName("explicit") val explicit: Boolean,
    @SerializedName("external_urls") val externalUrls: ExternalUrls,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<Image>,
    @SerializedName("languages") val languages: List<String>,
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("name") val name: String,
    @SerializedName("narrators") val narrators: List<Narrator>,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String,
    @SerializedName("total_chapters") val totalChapters: Int
) : Parcelable

@Parcelize
data class Author(
    @SerializedName("name") val name: String
) : Parcelable

@Parcelize
data class Narrator(
    @SerializedName("name") val name: String
) : Parcelable

@Parcelize
data class ExternalIds(
    @SerializedName("isrc") val isrc: String,
    @SerializedName("ean") val ean: String,
    @SerializedName("upc") val upc: String
) : Parcelable