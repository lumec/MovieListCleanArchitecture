package com.labi2d.challenge.moviestwo.model

import android.app.Application
import com.labi2d.challenge.moviestwo.R

class FilmsRepository(application: Application) {

    private val apiKey = application.getString(R.string.api_key)

    suspend fun retrieveFilms() = RemoteConnection.service.listFilms(apiKey)
}