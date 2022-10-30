package com.labi2d.challenge.moviestwo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.labi2d.challenge.moviestwo.data.Error
import com.labi2d.challenge.moviestwo.domain.Film
import com.labi2d.challenge.moviestwo.data.toError
import com.labi2d.challenge.moviestwo.usecases.FindFilmsUseCase
import com.labi2d.challenge.moviestwo.usecases.GetFilmsUseCases
import com.labi2d.challenge.moviestwo.usecases.RequestFilmsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val findFilmsUseCase: FindFilmsUseCase,
    private val getFilmsUseCases: GetFilmsUseCases,
    private val requestFilmsUseCase: RequestFilmsUseCase,
    private val filmType: String
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getFilmsUseCases()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { films -> _state.update { UiState(films = films) } }
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val error = requestFilmsUseCase()
            if (error is Error) {
                _state.update { _state.value.copy(loading = false, error = error) }
            } else {
                findFilmsUseCase(filmType)
                    .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                    .collect { filteredFilms -> _state.update { UiState(films = filteredFilms) } }
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val films: List<Film>? = null,
        val error: Error? = null,
    )
}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val findFilmsUseCase: FindFilmsUseCase,
    private val getFilmsUseCases: GetFilmsUseCases,
    private val requestFilmsUseCase: RequestFilmsUseCase,
    private val filmType: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            findFilmsUseCase,
            getFilmsUseCases,
            requestFilmsUseCase,
            filmType
        ) as T
    }
}