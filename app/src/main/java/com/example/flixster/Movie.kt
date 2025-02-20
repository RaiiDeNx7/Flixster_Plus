package com.example.flixster

data class Movie(
    val title: String,
    val overview: String,
    val poster_path: String
) {
    fun getPosterUrl(): String {
        return "https://image.tmdb.org/t/p/w500$poster_path"
    }
}