package com.labi2d.challenge.moviestwo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.databinding.FragmentHomeBinding
import com.labi2d.challenge.moviestwo.model.FilmsRepository
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val filmsRepository by lazy { FilmsRepository(requireActivity().application) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(FragmentHomeBinding.bind(view)) {

            lifecycleScope.launch {
                progress.visibility = View.VISIBLE
                val films = filmsRepository.retrieveFilms().results
                filmRecyclerView.adapter = FilmsAdapter(films)
                progress.visibility = View.GONE
            }
        }
    }
}