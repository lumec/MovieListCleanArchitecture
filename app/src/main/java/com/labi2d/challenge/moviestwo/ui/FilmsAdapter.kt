package com.labi2d.challenge.moviestwo.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.basicDiffUtil
import com.labi2d.challenge.moviestwo.databinding.FilmItemViewBinding
import com.labi2d.challenge.moviestwo.inflate
import com.labi2d.challenge.moviestwo.loadIcon
import com.labi2d.challenge.moviestwo.model.Film

class FilmsAdapter(private val dataset: List<Film>) :
    ListAdapter<Film, FilmsAdapter.ViewHolder>(basicDiffUtil { old, new -> old.name == new.name }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.film_item_view, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = dataset[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int = dataset.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FilmItemViewBinding.bind(view)
        fun bind(film: Film) = with(binding) {
            filmTitle.text = film.name
            filmTitle.loadIcon(film.type)
        }
    }
}