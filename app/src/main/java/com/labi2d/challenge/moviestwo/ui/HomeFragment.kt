package com.labi2d.challenge.moviestwo.ui

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
import com.labi2d.challenge.moviestwo.model.FilmsRepository
import com.labi2d.challenge.moviestwo.visible
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_common) {

    private val safeArgs: HomeFragmentArgs by navArgs()

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(FilmsRepository(requireActivity().application), safeArgs.filmType)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCommonBinding.bind(view)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { binding.updateUI(it) }
            }
        }
    }

    private fun FragmentCommonBinding.updateUI(state: HomeViewModel.UiState) {
        progress.visible = state.loading
        filmRecyclerView.adapter = state.films?.let(::FilmsAdapter)
    }
}