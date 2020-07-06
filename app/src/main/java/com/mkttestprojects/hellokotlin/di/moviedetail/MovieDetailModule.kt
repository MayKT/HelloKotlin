package com.mkttestprojects.hellokotlin.di.moviedetail

import com.mkttestprojects.hellokotlin.network.MovieDetailApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MovieDetailModule {

    @MovieDetailScope
    @Provides
    fun provideMovieDetailApi(retrofit: Retrofit) :MovieDetailApi{
        return retrofit.create(MovieDetailApi ::class.java)
    }


}