package com.mkttestprojects.hellokotlin.repository.main.home

import com.mkttestprojects.hellokotlin.models.MovieListInfo
import com.mkttestprojects.hellokotlin.models.MovieListModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.network.SearchApi
import com.mkttestprojects.hellokotlin.util.AppConstants.DEVELOPER_KEY
import io.reactivex.Flowable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchRepository @Inject constructor(private val searchApi: SearchApi) {

    fun getSearchResults(query : String , page : Int) : Flowable<Resource<MovieListModel>>{

        if(searchApi.getSearchResults(DEVELOPER_KEY,"en-Us",query,page) == null){
            return Flowable.just(Resource.error("Error null",MovieListModel()))
        }

        return searchApi.getSearchResults(DEVELOPER_KEY,"en-Us",query,page)!!
            .onErrorReturn(Function {
                val dummymodel = MovieListModel()
                dummymodel.results = ArrayList<MovieListInfo>()

                return@Function dummymodel
            })
            .map {
                    movielistmodels: MovieListModel ->
                if(movielistmodels != null){
                    if(movielistmodels.results.isEmpty()){
                        return@map Resource.error("Error", MovieListModel())
                    }
                }
                return@map Resource.success(movielistmodels)
            }
            .subscribeOn(Schedulers.io())
    }

}