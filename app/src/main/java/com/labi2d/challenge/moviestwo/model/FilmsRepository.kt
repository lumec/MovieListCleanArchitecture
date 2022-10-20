package com.labi2d.challenge.moviestwo.model

import android.app.Application
import com.labi2d.challenge.moviestwo.R

class FilmsRepository(application: Application, private val type: String) {

    private val apiKey = application.getString(R.string.api_key)

    private suspend fun listFilms() = RemoteConnection.service.listFilms(apiKey)

    suspend fun retrieveFilms(): List<Film> {
        var filteredList = listFilms().results.filter { it.type.toString() in type }
        if(filteredList.isEmpty()) {
            filteredList = listFilms().results
        }
        return filteredList
    }

}