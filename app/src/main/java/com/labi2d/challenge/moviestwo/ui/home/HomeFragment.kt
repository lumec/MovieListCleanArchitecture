package com.labi2d.challenge.moviestwo.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.databinding.FragmentCommonBinding
import com.labi2d.challenge.moviestwo.data.FilmsRepository
import com.labi2d.challenge.moviestwo.ui.common.app
import kotlinx.coroutines.launch
import com.labi2d.challenge.moviestwo.data.Error
import com.labi2d.challenge.moviestwo.usecases.FindFilmsUseCase
import com.labi2d.challenge.moviestwo.usecases.GetFilmsUseCases
import com.labi2d.challenge.moviestwo.usecases.RequestFilmsUseCase

class HomeFragment : Fragment(R.layout.fragment_common) {

    private val safeArgs: HomeFragmentArgs by navArgs()
    private val adapter = FilmsAdapter()

    private val viewModel: HomeViewModel by viewModels {
        val repository = FilmsRepository(requireActivity().app)
        HomeViewModelFactory(
            FindFilmsUseCase(repository),
            GetFilmsUseCases(repository),
            RequestFilmsUseCase(repository),
            safeArgs.filmType
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCommonBinding.bind(view).apply {
            filmRecyclerView.adapter = adapter
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onUiReady()
                viewModel.state.collect { binding.updateUI(it) }
            }
        }
    }

    private fun FragmentCommonBinding.updateUI(state: HomeViewModel.UiState) {
        loading = state.loading
        films = state.films
        error = state.error?.let { errorToString(it) }
    }

    private fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context?.getString(R.string.connectivity_error)
        is Error.Server -> context?.getString(R.string.server_error) + error.code
        is Error.Unknown -> context?.getString(R.string.unknown_error) + error.message
    }
}