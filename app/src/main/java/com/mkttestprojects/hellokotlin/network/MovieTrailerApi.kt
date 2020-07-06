package com.mkttestprojects.hellokotlin.network

import com.mkttestprojects.hellokotlin.models.MovieTrailerModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieTrailerApi {
    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer(@Path("movie_id") movieId: Int,
                        @Query("api_key") apiKey: String
    ): Flowable<MovieTrailerModel>
}