package com.labi2d.challenge.moviestwo.model

data class RemoteResponse(
    val results: List<Show?>?
)

data class Show(
    val name: String?,
    val type: String?
)