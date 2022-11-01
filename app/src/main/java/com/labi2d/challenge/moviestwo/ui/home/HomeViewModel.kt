package com.labi2d.challenge.moviestwo.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.labi2d.challenge.domain.Film
import com.labi2d.challenge.domain.Error
import com.labi2d.challenge.moviestwo.framework.toError
import com.labi2d.challenge.usecases.FindFilmsUseCase
import com.labi2d.challenge.usecases.GetFilmsUseCases
import com.labi2d.challenge.usecases.RequestFilmsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val findFilmsUseCase: FindFilmsUseCase,
    private val getFilmsUseCases: GetFilmsUseCases,
    private val requestFilmsUseCase: RequestFilmsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    private val filmType = HomeFragmentArgs.fromSavedStateHandle(savedStateHandle).filmType
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