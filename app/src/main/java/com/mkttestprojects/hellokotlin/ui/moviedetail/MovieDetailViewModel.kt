package com.mkttestprojects.hellokotlin.ui.moviedetail

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.mkttestprojects.hellokotlin.models.DetailMovieListModel
import com.mkttestprojects.hellokotlin.models.MovieListModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.repository.moviedetail.MovieDeatilRepository
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val moviedetailRepository: MovieDeatilRepository) : ViewModel() {

    lateinit var movieDetailData : MediatorLiveData<Resource<DetailMovieListModel>>

    lateinit var similarMovies : MediatorLiveData<Resource<MovieListModel>>

    var movieId : Int = 0

    fun observeMovieDetail(id : Int) : MediatorLiveData<Resource<DetailMovieListModel>>{
        movieDetailData = MediatorLiveData()
        movieDetailData.value = Resource.loading(DetailMovieListModel())

       val source = LiveDataReactiveStreams.fromPublisher(
           moviedetailRepository.getMovieDetailByMovieId(id)
       )

        movieDetailData.addSource(source){
            movieDetailData.value = it
            movieDetailData.removeSource(source)
        }

        return movieDetailData
    }

    fun observeSimilarMoviesById(id: Int) : MediatorLiveData<Resource<MovieListModel>>{
        similarMovies = MediatorLiveData()
        similarMovies.value = Resource.loading(MovieListModel())

        val source = LiveDataReactiveStreams.fromPublisher(
            moviedetailRepository.getSimilarMoviesById(id)
        )

        similarMovies.addSource(source){
            similarMovies.value = it
            similarMovies.removeSource(source)
        }
        return similarMovies
    }

}