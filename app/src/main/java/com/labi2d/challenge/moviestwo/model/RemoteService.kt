package com.labi2d.challenge.moviestwo.model

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteService {

    @GET("{api_key}")
    suspend fun listShows(@Path("api_key") apiKey: String): RemoteResponse

}