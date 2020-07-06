package com.mkttestprojects.hellokotlin.di

import com.mkttestprojects.hellokotlin.di.main.MainFragmentBuildersModule
import com.mkttestprojects.hellokotlin.di.main.MainModule
import com.mkttestprojects.hellokotlin.di.main.MainScope
import com.mkttestprojects.hellokotlin.di.main.MainViewModelModule
import com.mkttestprojects.hellokotlin.di.moviedetail.MovieDetailModule
import com.mkttestprojects.hellokotlin.di.moviedetail.MovieDetailScope
import com.mkttestprojects.hellokotlin.di.moviedetail.MovieDetailViewModelModule
import com.mkttestprojects.hellokotlin.di.movietrailer.MovieTrailerModule
import com.mkttestprojects.hellokotlin.di.movietrailer.MovieTrailerScope
import com.mkttestprojects.hellokotlin.di.movietrailer.MovieTrailerViewModelModule
import com.mkttestprojects.hellokotlin.ui.moviedetail.MovieDetailActivity
import com.mkttestprojects.hellokotlin.ui.main.MainActivity
import com.mkttestprojects.hellokotlin.ui.movietrailer.MovieTrailerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(modules = [
    MainModule ::class,
    MainViewModelModule ::class,
    MainFragmentBuildersModule :: class])
    abstract fun contributeMainActivity() : MainActivity

    @MovieDetailScope
    @ContributesAndroidInjector(modules = [
    MovieDetailModule ::class,
    MovieDetailViewModelModule ::class
    ])
    abstract fun contributeMovieDetailActivity () : MovieDetailActivity

    @MovieTrailerScope
    @ContributesAndroidInjector(modules = [
    MovieTrailerViewModelModule ::class,
    MovieTrailerModule ::class])
    abstract fun contributeMovieTrailerActivity() : MovieTrailerActivity
}