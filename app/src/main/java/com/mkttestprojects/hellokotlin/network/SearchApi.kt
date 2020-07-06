package com.mkttestprojects.hellokotlin.network

import com.mkttestprojects.hellokotlin.models.MovieListModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/movie")
    fun getSearchResults(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Flowable<MovieListModel>
}