package com.mkttestprojects.hellokotlin.repository.moviedetail

import com.mkttestprojects.hellokotlin.models.DetailMovieListModel
import com.mkttestprojects.hellokotlin.models.MovieListInfo
import com.mkttestprojects.hellokotlin.models.MovieListModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.network.MovieDetailApi
import com.mkttestprojects.hellokotlin.util.AppConstants.DEVELOPER_KEY
import com.mkttestprojects.hellokotlin.util.AppConstants.API_ERROR
import io.reactivex.Flowable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDeatilRepository @Inject constructor(private val movieDetailApi: MovieDetailApi){

    fun getMovieDetailByMovieId(movieId : Int) : Flowable<Resource<DetailMovieListModel>> {

        if(movieDetailApi.getMovieDetails(movieId, DEVELOPER_KEY) == null){
            return Flowable.just(Resource.error("Error null", DetailMovieListModel()))
        }
        return movieDetailApi.getMovieDetails(movieId,DEVELOPER_KEY)!!
            .onErrorReturn(Function {
                val dummymodel = DetailMovieListModel()
                dummymodel.id = -1

                return@Function dummymodel})
            .map {movieListModels : DetailMovieListModel ->
                if(movieListModels != null){
                    if(movieListModels.id == -1){
                        return@map Resource.error(API_ERROR,DetailMovieListModel())
                    }
                }
                return@map Resource.success(movieListModels)
            }
            .subscribeOn(Schedulers.io())
    }

    fun getSimilarMoviesById(movieId: Int) : Flowable<Resource<MovieListModel>>{
        if(movieDetailApi.getSimilarMoviesById(movieId, DEVELOPER_KEY) == null){
            return Flowable.just(Resource.error("Error null", MovieListModel()))
        }
        return movieDetailApi.getSimilarMoviesById(movieId, DEVELOPER_KEY) !!
            .onErrorReturn(Function {
                val movieListModel = MovieListModel()
                movieListModel.results = ArrayList<MovieListInfo>()
                return@Function movieListModel
            })
            .map {
                if(it != null){
                    if(it.results.isEmpty()){
                        return@map Resource.error(API_ERROR,MovieListModel())
                    }
                }
                return@map Resource.success(it)
            }
            .subscribeOn(Schedulers.io())

    }

}