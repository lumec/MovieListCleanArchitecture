package com.labi2d.challenge.moviestwo.model

import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {

    @GET("{api_key}")
    suspend fun listFilms(@Path("api_key") apiKey: String): RemoteResponse

}