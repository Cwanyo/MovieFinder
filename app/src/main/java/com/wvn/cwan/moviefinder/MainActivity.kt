package com.wvn.cwan.moviefinder

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val TAG = "WVN"

    val baseUrl = "http://www.majorcineplex.com/apis/"

    var movieList = ArrayList<Movie>()
    var favMovieList = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_allmovies.setOnClickListener { showAllMovies() }
        btn_favorite.setOnClickListener { showFavoriteMoview() }

        showAllMovies()
    }

    private fun showAllMovies() {

        //reset list
        movieList.clear()

        var retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var movieService = retrofit.create(MovieService::class.java)
        var call = movieService.getMovies()

        call.enqueue(object : Callback<MoviesResult> {
            override fun onFailure(call: Call<MoviesResult>?, t: Throwable?) {
                Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_LONG).show()
                Log.e(TAG, "Error http request", t)
            }

            override fun onResponse(call: Call<MoviesResult>?, response: Response<MoviesResult>?) {
                Toast.makeText(applicationContext, "LOADING", Toast.LENGTH_LONG).show()

                val moviesResult = response?.body() as? MoviesResult
                val movies = moviesResult?.movies
                Log.d(TAG, "Movies size " + movies?.size)

                if (movies != null) {
                    Log.d(TAG, "Not null")
                    for (m in movies) {
                        movieList.add(Movie(m.id, "http://www.majorcineplex.com" + m.poster, m.title, m.genre, m.releaseDate, m.synopsis))
                    }

                    lv_movies.adapter = MovieAdapter(applicationContext, movieList)
                } else {
                    Toast.makeText(applicationContext, "Empty", Toast.LENGTH_LONG).show()
                }

            }

        })

    }

    private fun showFavoriteMoview() {
        lv_movies.adapter = MovieAdapter(applicationContext, favMovieList)
    }
}


