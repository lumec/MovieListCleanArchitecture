package com.labi2d.challenge.moviestwo.ui.common

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.domain.Film
import com.labi2d.challenge.moviestwo.ui.home.FilmsAdapter

@BindingAdapter("icon")
fun TextView.loadIcon(type: String?) {
    val icon = when(type) {
        "MOVIE" -> R.drawable.ic_movies
        else -> R.drawable.ic_series
    }
    setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
}

@BindingAdapter("items")
fun RecyclerView.setItems(films: List<Film>?) {
    if (films != null) {
        (adapter as? FilmsAdapter)?.submitList(films)
    }
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean?) {
    visibility = if (visible == true) View.VISIBLE else View.GONE
}
