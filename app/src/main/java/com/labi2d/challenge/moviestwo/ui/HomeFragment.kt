package com.labi2d.challenge.moviestwo.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.databinding.FragmentHomeBinding
import com.labi2d.challenge.moviestwo.model.RemoteConnection
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(FragmentHomeBinding.bind(view)) {

            lifecycleScope.launch {
                progress.visibility = View.VISIBLE
                val films = RemoteConnection.service.listFilms(getString(R.string.api_key))
                filmRecyclerView.adapter = FilmsAdapter(films.results)
                progress.visibility = View.GONE
            }
        }
    }
}