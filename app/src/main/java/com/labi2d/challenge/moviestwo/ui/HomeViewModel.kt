package com.labi2d.challenge.moviestwo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.labi2d.challenge.moviestwo.model.Film
import com.labi2d.challenge.moviestwo.model.FilmsRepository
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
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(films = filmsRepository.retrieveFilms(filmType))
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