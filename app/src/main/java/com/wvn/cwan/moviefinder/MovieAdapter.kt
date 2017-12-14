package com.wvn.cwan.moviefinder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_lv_movies.view.*
import java.util.*

/**
 * Created by C.wan_yo on 14-Dec-17.
 */
class MovieAdapter: BaseAdapter {

    var context: Context? = null
    var movieList = ArrayList<Movie>()

    constructor(context: Context?, movieList: ArrayList<Movie>) : super() {
        this.context = context
        this.movieList = movieList
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(this.context)
        var row = layoutInflater.inflate(R.layout.row_lv_movies, null)
        var currMovie = this.movieList[p0]

        row.tv_date.text = currMovie.releaseDate
        row.tv_genre.text = currMovie.genre
        row.tv_title.text = currMovie.title

        Picasso.with(this.context).load(currMovie.poster).into(row.img_movie)

        row.setOnClickListener { showMoviesDetails(currMovie) }

        return row
    }

    override fun getItem(p0: Int): Any {
        return this.movieList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return this.movieList.size
    }

    fun showMoviesDetails(movie: Movie){
        Toast.makeText(this.context,"Cicked on: "+movie.id,Toast.LENGTH_LONG).show()

        val i = Intent(this.context, MovieDetail::class.java)
        var b = Bundle()
        b.putString("title",movie.title)
        b.putString("genre",movie.genre)
        b.putString("poster",movie.poster)
        b.putString("date",movie.releaseDate)
        b.putString("synopsis",movie.synopsis)
        b.putInt("id",movie.id)

        i.putExtras(b)
        this.context?.startActivity(i)

    }
}