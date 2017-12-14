package com.wvn.cwan.moviefinder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_detail.*
import kotlinx.android.synthetic.main.row_lv_movies.view.*

class MovieDetail : AppCompatActivity() {

    val TAG = "WVN"

    var id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)

        val bundle = intent.extras

        if (bundle != null) {
            id = bundle.getInt("id")
            Picasso.with(applicationContext).load(bundle.getString("poster")).into(d_img_movie)
            d_tv_date.text = bundle.getString("date")
            d_tv_genre.text = bundle.getString("genre")
            d_tv_synopsis.text = bundle.getString("synopsis")
            d_tv_title.text = bundle.getString("title")
        }

        d_btn_addfavorite.setOnClickListener{addFavoriteList()}
    }

    private fun addFavoriteList(){

    }
}
