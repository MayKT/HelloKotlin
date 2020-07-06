package com.mkttestprojects.hellokotlin.di.moviedetail

import androidx.lifecycle.ViewModel
import com.mkttestprojects.hellokotlin.di.ViewModelKey
import com.mkttestprojects.hellokotlin.ui.moviedetail.MovieDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieDetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel ::class)
    abstract fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel) : ViewModel
}