package com.wvn.cwan.moviefinder

import retrofit2.Call
import retrofit2.http.GET
import java.util.*

/**
 * Created by C.wan_yo on 14-Dec-17.
 */
interface MovieService {

    @GET("get_movie_avaiable")
    fun getMovies(): Call<MoviesResult>

}