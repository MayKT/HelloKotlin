package com.mkttestprojects.hellokotlin.network

import com.mkttestprojects.hellokotlin.models.MovieListModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Flowable<MovieListModel>?

}