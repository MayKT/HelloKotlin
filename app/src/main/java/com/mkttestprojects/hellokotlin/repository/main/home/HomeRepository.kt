package com.mkttestprojects.hellokotlin.repository.main.home

import com.mkttestprojects.hellokotlin.models.MovieListInfo
import com.mkttestprojects.hellokotlin.models.MovieListModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.network.HomeApi
import com.mkttestprojects.hellokotlin.util.AppConstants.DEVELOPER_KEY
import com.mkttestprojects.hellokotlin.util.AppConstants.API_ERROR
import io.reactivex.Flowable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeRepository @Inject constructor(private val homeApi: HomeApi) {

    fun getNowPlayingMovies(page : Int) : Flowable<Resource<MovieListModel>> {

        if(homeApi.getNowPlayingMovies(DEVELOPER_KEY,"en-US",page) == null){
            return Flowable.just(Resource.error("Error null", MovieListModel()))
        }

        return homeApi.getNowPlayingMovies(DEVELOPER_KEY, "en-US", page)!!

            .onErrorReturn(Function {
                val dummymodel = MovieListModel()
                dummymodel.results = ArrayList<MovieListInfo>()

            return@Function dummymodel})

            .map { movielistmodels: MovieListModel ->
                    if(movielistmodels.results.isEmpty()){
                        return@map Resource.error(API_ERROR, MovieListModel())
                    }
                return@map Resource.success(movielistmodels)
            }
            .subscribeOn(Schedulers.io())
    }

}