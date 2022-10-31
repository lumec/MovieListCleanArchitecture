package com.labi2d.challenge.moviestwo.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.labi2d.challenge.domain.Error
import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.databinding.FragmentCommonBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment(R.layout.fragment_common) {

    private val safeArgs: HomeFragmentArgs by navArgs()
    private val adapter = FilmsAdapter()

    private val viewModel: HomeViewModel by viewModel{
        parametersOf(safeArgs.filmType)
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