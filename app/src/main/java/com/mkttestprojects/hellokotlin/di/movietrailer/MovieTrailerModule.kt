package com.mkttestprojects.hellokotlin.di.movietrailer

import com.mkttestprojects.hellokotlin.network.MovieTrailerApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MovieTrailerModule {

    @MovieTrailerScope
    @Provides
    fun provideMovieTrailerApi(retrofit: Retrofit) : MovieTrailerApi{
        return retrofit.create(MovieTrailerApi ::class.java)
    }
}