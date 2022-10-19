package com.labi2d.challenge.moviestwo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.model.RemoteConnection
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.show_recycler_view)
        lifecycleScope.launch {
            val shows = RemoteConnection.service.listShows(getString(R.string.api_key))
            recyclerView.adapter = ShowsAdapter(shows.results)
            Log.e("log", "size ${shows.results?.size}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}