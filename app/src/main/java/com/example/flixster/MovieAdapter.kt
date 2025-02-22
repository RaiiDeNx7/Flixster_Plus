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
    private val people: List<PersonClass>,
    private val listener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MovieViewAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.movieTitle) // Renamed to "name"
        val profileImage: ImageView = view.findViewById(R.id.moviePoster) // Renamed to "profileImage"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val person = people[position]

        holder.name.text = person.name

        Glide.with(holder.itemView.context)
            .load(person.getProfileUrl())
            .into(holder.profileImage)
    }

    override fun getItemCount(): Int = people.size
}


