package com.mkttestprojects.hellokotlin.network

import com.mkttestprojects.hellokotlin.models.DetailMovieListModel
import com.mkttestprojects.hellokotlin.models.MovieListModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailApi {

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Flowable<DetailMovieListModel>?

    @GET("movie/{movie_id}/similar")
    fun getSimilarMoviesById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ) : Flowable<MovieListModel>

}