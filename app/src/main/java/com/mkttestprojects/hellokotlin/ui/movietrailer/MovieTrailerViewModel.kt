package com.mkttestprojects.hellokotlin.ui.movietrailer

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.mkttestprojects.hellokotlin.models.MovieTrailerModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.repository.movietrailer.MovieTrailerRepository
import javax.inject.Inject

class MovieTrailerViewModel @Inject constructor(private val movieTrailerRepository: MovieTrailerRepository) : ViewModel() {

    lateinit var movieTrailerData: MediatorLiveData<Resource<MovieTrailerModel>>

    var movieId : Int = 0

    fun observeMovieTrailer(id : Int) : MediatorLiveData<Resource<MovieTrailerModel>>{

        movieTrailerData = MediatorLiveData()

        movieTrailerData.value =Resource.loading(MovieTrailerModel())

        val source = LiveDataReactiveStreams.fromPublisher(
            movieTrailerRepository.getMovieTrailerById(id)
        )

        movieTrailerData.addSource(source){
            movieTrailerData.value = it
            movieTrailerData.removeSource(source)
        }

        return movieTrailerData
    }

}