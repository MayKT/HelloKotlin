package com.mkttestprojects.hellokotlin.repository.movietrailer

import com.mkttestprojects.hellokotlin.models.MovieTrailerInfoModel
import com.mkttestprojects.hellokotlin.models.MovieTrailerModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.network.MovieTrailerApi
import com.mkttestprojects.hellokotlin.util.AppConstants.DEVELOPER_KEY
import io.reactivex.Flowable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieTrailerRepository @Inject constructor(private val movieTrailerApi: MovieTrailerApi) {

    fun getMovieTrailerById(id : Int) : Flowable<Resource<MovieTrailerModel>>{
        if(movieTrailerApi.getMovieTrailer(id,DEVELOPER_KEY) == null){
            return Flowable.just(Resource.error("Error null", MovieTrailerModel()))
        }

        return movieTrailerApi.getMovieTrailer(id, DEVELOPER_KEY)!!
            .onErrorReturn(Function {
                val movieTrailerModel = MovieTrailerModel()
                movieTrailerModel.results = ArrayList<MovieTrailerInfoModel>()
                return@Function movieTrailerModel
            })
            .map {
                if(it != null){
                    if(it.results.isEmpty()){
                        return@map Resource.error("Error",MovieTrailerModel())
                    }
                }
                return@map Resource.success(it)
            }
            .subscribeOn(Schedulers.io())
    }
}