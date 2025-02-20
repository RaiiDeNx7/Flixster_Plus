package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class MovieViewAdapter(
    private val movies: List<Movie>,
    private val listener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MovieViewAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.movieTitle)
        val overview: TextView = view.findViewById(R.id.movieOverview)
        val poster: ImageView = view.findViewById(R.id.moviePoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.title.text = movie.title
        holder.overview.text = movie.overview

        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500" + movie.poster_path) // Correct image URL
            .into(holder.poster)
    }


    override fun getItemCount(): Int = movies.size
}


