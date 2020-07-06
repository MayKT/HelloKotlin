package com.mkttestprojects.hellokotlin.ui.main.home

import androidx.lifecycle.*
import com.mkttestprojects.hellokotlin.models.MovieListModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.repository.main.home.HomeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(val homeRepository : HomeRepository) : ViewModel() {

    lateinit var movieList : MediatorLiveData<Resource<MovieListModel>>

    var page : Int = 1

    fun observeMovies() : MediatorLiveData<Resource<MovieListModel>>{

        movieList = MediatorLiveData()

        movieList.value = Resource.loading(MovieListModel())

        val source = LiveDataReactiveStreams.fromPublisher(
            this.homeRepository.getNowPlayingMovies(page))

        movieList.addSource(source){
            movieList.value = it
            movieList.removeSource(source)
        }

        return movieList
    }

    companion object {
        private const val TAG = "HomeFragmentViewModel"
    }

}