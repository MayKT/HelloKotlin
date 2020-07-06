package com.mkttestprojects.hellokotlin.di.movietrailer

import androidx.lifecycle.ViewModel
import com.mkttestprojects.hellokotlin.di.ViewModelKey
import com.mkttestprojects.hellokotlin.ui.movietrailer.MovieTrailerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieTrailerViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieTrailerViewModel ::class)
    abstract fun bindMovieTrailerViewModel(movieTrailerViewModel: MovieTrailerViewModel) : ViewModel
}