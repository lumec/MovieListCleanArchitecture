package com.labi2d.challenge.moviestwo.di

import com.labi2d.challenge.data.FilmsRepository
import com.labi2d.challenge.data.datasource.FilmLocalDataSource
import com.labi2d.challenge.data.datasource.FilmRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
object DataModule {

    @Provides
    fun providerFilmsRepository(
        localDataSource: FilmLocalDataSource,
        remoteDataSource: FilmRemoteDataSource
    ) = FilmsRepository(localDataSource, remoteDataSource)
}