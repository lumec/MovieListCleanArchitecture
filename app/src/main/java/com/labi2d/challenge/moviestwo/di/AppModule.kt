package com.labi2d.challenge.moviestwo.di

import android.app.Application
import androidx.room.Room
import com.labi2d.challenge.data.datasource.FilmLocalDataSource
import com.labi2d.challenge.data.datasource.FilmRemoteDataSource
import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.framework.database.FilmDatabase
import com.labi2d.challenge.moviestwo.framework.database.FilmRoomDataSource
import com.labi2d.challenge.moviestwo.framework.server.FilmServerDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
    @Singleton
    fun provideFilmDao(db: FilmDatabase) = db.filmDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSource: FilmRoomDataSource): FilmLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: FilmServerDataSource): FilmRemoteDataSource
}