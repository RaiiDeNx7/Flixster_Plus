package com.example.flixster

data class PersonClass(
    val name: String,
    val profile_path: String
) {
    fun getProfileUrl(): String {
        return "https://image.tmdb.org/t/p/w500$profile_path"
    }
}
