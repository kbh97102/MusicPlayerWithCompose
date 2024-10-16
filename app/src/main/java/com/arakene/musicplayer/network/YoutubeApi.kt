package com.arakene.musicplayer.network

import com.arakene.musicplayer.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET(EndPoints.SEARCH)
    suspend fun search(
        @Query("part") part: String = "snippet",
        @Query("forMine") forContentOwner: Boolean = true,
        @Query("key") apiKey: String = "AIzaSyCnLgV_F4vl1QNligvuKZItwmzOILcYBh4",
        @Query("access_token") token: String = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImE1MGY2ZTcwZWY0YjU0OGE1ZmQ5MTQyZWVjZDFmYjhmNTRkY2U5ZWUiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI5MTU0NjUwMzA4MTYtNGI4Z2w2YmxkcHM0aDg5bmluZjU5MmcwOGh2bHZvbDYuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI5MTU0NjUwMzA4MTYtbmx2NGE0c2JpY3FodHJlOTk5b25vbHN2b2ZrZXRzNjguYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTU1MjQ1NzU2NzExOTAwNTU5NzUiLCJlbWFpbCI6ImtiaDk3MTAyQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYW1lIjoi6rCV67O07ZuIIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0t0aXFyX09rV3ZsbTdIeXJIWjUxcl9RX2lCVkhaeTNUSVA2aHJJcllSS2JNVnRKUT1zOTYtYyIsImdpdmVuX25hbWUiOiLrs7Ttm4giLCJmYW1pbHlfbmFtZSI6IuqwlSIsImlhdCI6MTcyODkxNDMwMCwiZXhwIjoxNzI4OTE3OTAwfQ.i8PFruRPJfRsHQoig1BIQVFoscoI43z97rhIC7HQKKAjhrpzLcFxFDu4hrEL_IUobWDJeCNRn6nsk7StD4dsGXLlk0R-pGyP-C_Tpqptvecdm40oBNFAnDTBnqGwUTkOsayYDjrW8aLuQEdk4qRcYWDeFrVeCjyXYr_iSJsPeYkd4jL40DuMndTxb-hptxke3dTo8j7gAyBl6-HYkC4dxvLta4RI9Ldoq-DbW1zhPxGMkunAHefY0GQ9cfovGzUCDPEtNtA6U6YzsBOzzTFE1SYCLdnKJgAYc5e1SXfeWYyLfG72loEhn19qdDX2YJqKjtELGB7kePc8i-MCOW35ng",
        @Query("type") type: String ="video",
        @Query("q") q: String
    ): Response<String>

}