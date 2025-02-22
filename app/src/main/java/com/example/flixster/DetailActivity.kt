package com.example.flixster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import android.util.Log

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        // Retrieve data from intent
        val personName = intent.getStringExtra("PERSON_NAME")
        val personImage = intent.getStringExtra("PERSON_IMAGE")

        // Find views
        val titleTextView = findViewById<TextView>(R.id.person_title)
        val imageView = findViewById<ImageView>(R.id.person_image)

        // Set data to views
        titleTextView.text = personName

        // Load profile image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/$personImage")
            .centerInside()
            .into(imageView)

        // Retrieve the ArrayList<KnownFor> from the intent
        val knownForList = intent.getParcelableArrayListExtra<KnownFor>("KNOWN_FOR_LIST")

        // Find views for "Known For" movie
        val movieTitle = findViewById<TextView>(R.id.movie_title)
        val overview = findViewById<TextView>(R.id.overview)
        val posterImage = findViewById<ImageView>(R.id.movie_poster)

        // Check if the knownForList is not null and has at least one item
        if (!knownForList.isNullOrEmpty()) {
            // Get the first movie in the knownFor list
            val knownForFirstItem = knownForList[0]

            // Set the movie title
            movieTitle.text = "Known For: ${knownForFirstItem.title}"

            // Set the movie overview
            overview.text = knownForFirstItem.overview

            // Load movie poster
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/${knownForFirstItem.posterPath}")
                .centerInside()
                .into(posterImage)
        } else {
            Log.e("DetailActivity", "KnownFor list is either null or empty")
        }
    }
}
