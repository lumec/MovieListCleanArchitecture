package com.labi2d.challenge.moviestwo.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Film(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val type: String
)