package com.labi2d.challenge.moviestwo.framework.server

import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {

    @GET("{api_key}")
    suspend fun listFilms(@Path("api_key") apiKey: String): RemoteResponse

}