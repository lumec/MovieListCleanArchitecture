package com.labi2d.challenge.moviestwo.di

import android.app.Application
import androidx.room.Room
import com.labi2d.challenge.data.datasource.FilmLocalDataSource
import com.labi2d.challenge.data.datasource.FilmRemoteDataSource
import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.framework.database.FilmDatabase
import com.labi2d.challenge.moviestwo.framework.database.FilmRoomDataSource
import com.labi2d.challenge.moviestwo.framework.server.FilmServerDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        FilmDatabase::class.java, "film-database"
    ).build()

    @Provides
    fun provideLocalDataSource(db: FilmDatabase): FilmLocalDataSource =
        FilmRoomDataSource(db.filmDao())

    @Provides
    fun provideRemoteDataSource(@ApiKey apiKey: String): FilmRemoteDataSource =
        FilmServerDataSource(apiKey)
}