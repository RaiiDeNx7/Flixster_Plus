package com.example.flixster

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//Test

class MainActivity : AppCompatActivity(), OnListFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MovieFragment())
            .commit()
    }

    override fun onMovieSelected(person: PersonClass) {
        // Handle person selection (e.g., show details or navigate to another screen)
    }
}

