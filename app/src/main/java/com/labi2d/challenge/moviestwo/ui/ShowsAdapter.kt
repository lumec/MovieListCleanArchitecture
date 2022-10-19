package com.labi2d.challenge.moviestwo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.model.Show

class ShowsAdapter(private val dataset: List<Show>) :
    RecyclerView.Adapter<ShowsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.movie_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.show_item_view, parent, false)

        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val show = dataset[position]
        holder.textView.text = show.name
    }

    override fun getItemCount(): Int = dataset.size
}