package com.labi2d.challenge.moviestwo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.labi2d.challenge.moviestwo.model.FilmsRepository
import com.labi2d.challenge.moviestwo.model.database.Film
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val filmsRepository: FilmsRepository,
    private val filmType: String
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            filmsRepository.films.collect { films ->
                _state.value = UiState(films = films)
            }
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            filmsRepository.requestFilms()
            filmsRepository.findByType(filmType).collect { filteredFilms ->
                if (filteredFilms.isNotEmpty()) {
                    _state.value = UiState(films = filteredFilms)
                } else {
                    filmsRepository.films.collect { films ->
                        _state.value = UiState(films = films)
                    }
                }
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val films: List<Film>? = null,
    )
}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val filmsRepository: FilmsRepository,
    private val filmType: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(filmsRepository, filmType) as T
    }
}