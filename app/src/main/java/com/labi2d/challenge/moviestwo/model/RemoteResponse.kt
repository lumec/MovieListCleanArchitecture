package com.labi2d.challenge.moviestwo.model

data class RemoteResponse(
    val results: List<RemoteFilm>
)

data class RemoteFilm(
    val name: String?,
    val type: String?
)