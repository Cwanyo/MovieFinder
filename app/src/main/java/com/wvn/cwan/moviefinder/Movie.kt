package com.wvn.cwan.moviefinder

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by C.wan_yo on 14-Dec-17.
 */

data class MoviesResult(
        var movies: ArrayList<Movie> = ArrayList()
)

data class Movie(
        @SerializedName("id") var id: Int = 0,
        @SerializedName("poster_ori") var poster: String = "",
        @SerializedName("title_en") var title: String = "",
        @SerializedName("genre") var genre: String = "",
        @SerializedName("release_date") var releaseDate: String = "",
        @SerializedName("synopsis_en") var synopsis: String = ""
)
