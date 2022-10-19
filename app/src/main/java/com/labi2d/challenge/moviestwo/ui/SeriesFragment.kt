package com.labi2d.challenge.moviestwo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.databinding.FragmentSeriesBinding
import com.labi2d.challenge.moviestwo.model.FilmsRepository
import kotlinx.coroutines.launch

class SeriesFragment : Fragment(R.layout.fragment_series) {

    private val filmsRepository by lazy { FilmsRepository(requireActivity().application) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(FragmentSeriesBinding.bind(view)) {

            lifecycleScope.launch {
                val progress = commonRecyclerView.progress
                val filmRecyclerView = commonRecyclerView.filmRecyclerView

                progress.visibility = View.VISIBLE

                val films = filmsRepository.retrieveFilms().results
                filmRecyclerView.adapter = FilmsAdapter(films)

                progress.visibility = View.GONE
            }
        }
    }

}