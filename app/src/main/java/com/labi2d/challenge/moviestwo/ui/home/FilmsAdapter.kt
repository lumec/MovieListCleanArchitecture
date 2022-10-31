package com.labi2d.challenge.moviestwo.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.ui.common.basicDiffUtil
import com.labi2d.challenge.moviestwo.databinding.FilmItemViewBinding
import com.labi2d.challenge.moviestwo.ui.common.inflate
import com.labi2d.challenge.domain.Film

class FilmsAdapter :
    ListAdapter<Film, FilmsAdapter.ViewHolder>(basicDiffUtil { old, new -> old.name == new.name }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.film_item_view, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = getItem(position)
        holder.bind(film)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FilmItemViewBinding.bind(view)
        fun bind(film: Film) {
            binding.film = film
        }
    }
}