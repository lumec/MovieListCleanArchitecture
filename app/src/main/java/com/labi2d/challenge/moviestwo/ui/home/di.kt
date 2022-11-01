package com.labi2d.challenge.moviestwo.ui.home

import com.labi2d.challenge.usecases.FindFilmsUseCase
import com.labi2d.challenge.usecases.GetFilmsUseCases
import com.labi2d.challenge.usecases.RequestFilmsUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class HomeFragmentModule(private val filmType: String) {

    @Provides
    fun homeViewModelFactoryProvider(
        findFilmsUseCase: FindFilmsUseCase,
        getFilmsUseCases: GetFilmsUseCases,
        requestFilmsUseCase: RequestFilmsUseCase
    ) = HomeViewModelFactory(
        findFilmsUseCase,
        getFilmsUseCases,
        requestFilmsUseCase,
        filmType
    )
}

@Subcomponent(modules = [HomeFragmentModule::class])
interface HomeFragmentComponent {
    val homeViewModelFactory: HomeViewModelFactory
}