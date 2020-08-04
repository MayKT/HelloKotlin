package com.mkttestprojects.hellokotlin.repository

import com.mkttestprojects.hellokotlin.TestUtil
import com.mkttestprojects.hellokotlin.models.DetailMovieListModel
import com.mkttestprojects.hellokotlin.models.MovieListInfo
import com.mkttestprojects.hellokotlin.models.MovieListModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.network.MovieDetailApi
import com.mkttestprojects.hellokotlin.repository.moviedetail.MovieDeatilRepository
import com.mkttestprojects.hellokotlin.util.AppConstants.API_ERROR
import com.mkttestprojects.hellokotlin.util.InstantExecutorExtension
import io.reactivex.Flowable
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExtendWith(InstantExecutorExtension::class)
class MovieDetailRepositoryTest {
    @Mock
    lateinit var movieDetailApi : MovieDetailApi

    lateinit var movieDetailRepository: MovieDeatilRepository

    @BeforeEach
    fun initBeforeEach(){
        MockitoAnnotations.initMocks(this)
        movieDetailRepository = MovieDeatilRepository(movieDetailApi)
    }

    @Test
    internal fun observeMovieDetail_returnSuccess() {
        //Arrange
        val detailModel = TestUtil.TEST_MOVIE_DETAIL_MODEL
        Mockito.`when`(movieDetailApi.getMovieDetails(anyInt(), anyString())).thenReturn(Flowable.just(detailModel))

        //Act
        val returnedValue = movieDetailRepository.getMovieDetailByMovieId(eq(anyInt())).blockingFirst()

        //Assert
        assertEquals(Resource.success(detailModel),returnedValue)
    }

    @Test
    internal fun observeMovieDetail_returnFailure() {
        //Arrange
        val detailModel = DetailMovieListModel()
        detailModel.id = -1
        Mockito.`when`(movieDetailApi.getMovieDetails(anyInt(), anyString())).thenReturn(Flowable.just(detailModel))

        //Act
        val returnedValue = movieDetailRepository.getMovieDetailByMovieId(eq(anyInt())).blockingFirst()

        //Assert
        assertEquals(Resource.error(API_ERROR,null),returnedValue)
    }

    @Test
    @Throws(Exception ::class)
    internal fun observeMovieDetail_returnFailure_throwsError() {
        // Arrange
        val detailModel = DetailMovieListModel()
        Mockito.`when`(movieDetailApi.getMovieDetails(anyInt(), anyString())).thenReturn(
            Flowable.error(Throwable(API_ERROR))
        )
        //Act
        val returnedValue = movieDetailRepository.getMovieDetailByMovieId(eq(anyInt())).blockingFirst()

        //Assert
        assertEquals(API_ERROR,returnedValue.message)

    }

    @Test
    internal fun observeSimilarMovies_returnSuccess() {
        //Arrange
        val movieModel = TestUtil.TEST_MOVIE_LIST_MODEL
        Mockito.`when`(movieDetailApi.getSimilarMoviesById(anyInt(), anyString())).thenReturn(
            Flowable.just(movieModel))

        //Act
        val returnedValue = movieDetailRepository.getSimilarMoviesById(eq(anyInt())).blockingFirst()

        //Assert
        assertEquals(Resource.success(movieModel),returnedValue)
    }

    @Test
    internal fun observeSimilarMovies_returnFailure() {
        //Arrange
        val detailModel = MovieListModel()
        detailModel.results = ArrayList<MovieListInfo>()
        Mockito.`when`(movieDetailApi.getSimilarMoviesById(anyInt(), anyString())).thenReturn(Flowable.just(detailModel))

        //Act
        val returnedValue = movieDetailRepository.getSimilarMoviesById(eq(anyInt())).blockingFirst()

        //Assert
        assertEquals(Resource.error(API_ERROR,null),returnedValue)    }

    @Test
    @Throws(Exception ::class)
    internal fun observeSimilarMovies_returnFailure_throwsException() {
        val movieListModel = MovieListModel()
        movieListModel.results = ArrayList<MovieListInfo>()
        Mockito.`when`(movieDetailApi.getSimilarMoviesById(anyInt(), anyString())).thenReturn(
            Flowable.error(Throwable(API_ERROR)))

        val returnedValue = movieDetailRepository.getSimilarMoviesById(eq(anyInt())).blockingFirst()

        assertEquals(API_ERROR,returnedValue.message)
    }

}