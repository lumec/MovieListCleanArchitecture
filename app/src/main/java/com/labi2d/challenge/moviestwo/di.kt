package com.labi2d.challenge.moviestwo

import android.app.Application
import androidx.room.Room
import com.labi2d.challenge.data.FilmsRepository
import com.labi2d.challenge.data.datasource.FilmLocalDataSource
import com.labi2d.challenge.data.datasource.FilmRemoteDataSource
import com.labi2d.challenge.moviestwo.framework.database.FilmDatabase
import com.labi2d.challenge.moviestwo.framework.database.FilmRoomDataSource
import com.labi2d.challenge.moviestwo.framework.server.FilmServerDataSource
import com.labi2d.challenge.moviestwo.ui.home.HomeViewModel
import com.labi2d.challenge.usecases.FindFilmsUseCase
import com.labi2d.challenge.usecases.GetFilmsUseCases
import com.labi2d.challenge.usecases.RequestFilmsUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDi() {
    startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@initDi)
        modules(appModule, dataModule, usecasesModule)
    }
}

private val appModule = module {
    single(named("apiKey")){androidApplication().getString(R.string.api_key)}

    single {
        Room.databaseBuilder(
            get(),
            FilmDatabase::class.java, "film-database"
        ).build()
    }

    single { get<FilmDatabase>().filmDao() }

    factory<FilmLocalDataSource> { FilmRoomDataSource(get()) }
    factory<FilmRemoteDataSource> { FilmServerDataSource(get(named("apiKey"))) }

    viewModel { (filmType: String) -> HomeViewModel(get(), get(), get(), filmType) }
}

private val dataModule = module {
    factory { FilmsRepository(get(), get()) }
}

private val usecasesModule = module {
    factory { FindFilmsUseCase(get()) }
    factory { GetFilmsUseCases(get()) }
    factory { RequestFilmsUseCase(get()) }
}