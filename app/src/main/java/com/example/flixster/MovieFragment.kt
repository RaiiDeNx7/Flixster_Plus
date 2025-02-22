package com.example.flixster

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class MovieFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var listener: OnListFragmentInteractionListener? = null
    private val apiKey = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        // Set the LayoutManager to GridLayoutManager with 2 columns
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        fetchPeople() // Fetch popular people
        return view
    }

    private fun fetchPeople() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(MovieApiService::class.java)

        apiService.popularPeople(apiKey).enqueue(object : Callback<PersonResponse> {
            override fun onResponse(call: Call<PersonResponse>, response: Response<PersonResponse>) {
                if (response.isSuccessful) {
                    response.body()?.results?.let {
                        recyclerView.adapter = MovieViewAdapter(it, listener)
                    }
                }
            }

            override fun onFailure(call: Call<PersonResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }

    interface MovieApiService {
        @GET("person/popular")
        fun popularPeople(@Query("api_key") apiKey: String): Call<PersonResponse>
    }

    data class PersonResponse(val results: List<PersonClass>)
}

