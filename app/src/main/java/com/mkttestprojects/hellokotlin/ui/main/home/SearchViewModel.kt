package com.mkttestprojects.hellokotlin.ui.main.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.mkttestprojects.hellokotlin.models.MovieListModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.repository.main.home.SearchRepository
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel(){

    lateinit var searchResults : MediatorLiveData<Resource<MovieListModel>>

    var page  = 1

    fun observeSearchResults(query : String) : MediatorLiveData<Resource<MovieListModel>>{

        searchResults = MediatorLiveData()

        searchResults.value = Resource.loading(MovieListModel())

        val source = LiveDataReactiveStreams.fromPublisher(
            this.searchRepository.getSearchResults(query,page)
        )

        searchResults.addSource(source){
            searchResults.value = it
            searchResults.removeSource(source)
        }
        return searchResults
    }
}