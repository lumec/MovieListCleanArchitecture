package com.labi2d.challenge.moviestwo.model

data class RemoteResponse(
    val results: List<Film>
)

data class Film(
    val name: String?,
    val type: String?
)