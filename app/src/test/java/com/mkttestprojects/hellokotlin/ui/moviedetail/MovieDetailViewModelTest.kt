package com.mkttestprojects.hellokotlin.ui.moviedetail

import com.mkttestprojects.hellokotlin.LiveDataTestUtil
import com.mkttestprojects.hellokotlin.TestUtil
import com.mkttestprojects.hellokotlin.models.DetailMovieListModel
import com.mkttestprojects.hellokotlin.models.MovieListModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.repository.moviedetail.MovieDeatilRepository
import com.mkttestprojects.hellokotlin.util.InstantExecutorExtension
import io.reactivex.Flowable
import io.reactivex.internal.operators.single.SingleToFlowable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExtendWith(InstantExecutorExtension::class)
class MovieDetailViewModelTest {

    @Mock
    lateinit var moviedetailRepository: MovieDeatilRepository

    lateinit var movieDetailViewModel: MovieDetailViewModel

    @BeforeEach
    fun init(){
        MockitoAnnotations.initMocks(this)
        movieDetailViewModel = MovieDetailViewModel(moviedetailRepository)
    }

    @Test
    internal fun observeMovieDetail_returnDetails() {
        val movieDetailModel = TestUtil.TEST_MOVIE_DETAIL_MODEL
        val liveDataTestUtil: LiveDataTestUtil<Resource<DetailMovieListModel>> = LiveDataTestUtil()
        val returnedData: Flowable<Resource<DetailMovieListModel>> =
            SingleToFlowable.just(Resource.success(movieDetailModel))
        Mockito.`when`(moviedetailRepository.getMovieDetailByMovieId(anyInt()))
            .thenReturn(returnedData)

        val returnedValue: Resource<DetailMovieListModel> = liveDataTestUtil.getValue( movieDetailViewModel.observeMovieDetail(
            anyInt()))

        Assertions.assertEquals(Resource.success(movieDetailModel),returnedValue)
    }

    @Test
    internal fun observeMovieDetail_returnNull() {
        val movieDetailModel = DetailMovieListModel()
        val liveDataTestUtil: LiveDataTestUtil<Resource<DetailMovieListModel>> = LiveDataTestUtil()
        val returnedData: Flowable<Resource<DetailMovieListModel>> =
            SingleToFlowable.just(Resource.error("Error",movieDetailModel))
        Mockito.`when`(moviedetailRepository.getMovieDetailByMovieId(anyInt()))
            .thenReturn(returnedData)

        val returnedValue: Resource<DetailMovieListModel> = liveDataTestUtil.getValue( movieDetailViewModel.observeMovieDetail(
            anyInt()))

        Assertions.assertEquals(Resource.error("Error",movieDetailModel),returnedValue)    }


    @Test
    internal fun observeSimilarMovies_returnSuccess() {
        val movieModel = TestUtil.TEST_MOVIE_LIST_MODEL
        val liveDataTestUtil: LiveDataTestUtil<Resource<MovieListModel>> = LiveDataTestUtil()
        val returnedData: Flowable<Resource<MovieListModel>> =
            SingleToFlowable.just(Resource.success(movieModel))
        Mockito.`when`(moviedetailRepository.getSimilarMoviesById(anyInt()))
            .thenReturn(returnedData)

        val returnedValue : Resource<MovieListModel> = liveDataTestUtil.getValue(movieDetailViewModel.observeSimilarMoviesById(
            anyInt()
        ))

        Assertions.assertEquals(Resource.success(movieModel),returnedValue)

    }

    @Test
    internal fun observeSimilarMovies_returnFailure() {
        val movieModel = MovieListModel()
        val liveDataTestUtil: LiveDataTestUtil<Resource<MovieListModel>> = LiveDataTestUtil()
        val returnedData: Flowable<Resource<MovieListModel>> =
            SingleToFlowable.just(Resource.error("Error",movieModel))
        Mockito.`when`(moviedetailRepository.getSimilarMoviesById(anyInt()))
            .thenReturn(returnedData)

        val returnedValue : Resource<MovieListModel> = liveDataTestUtil.getValue(movieDetailViewModel.observeSimilarMoviesById(
            anyInt()
        ))

        Assertions.assertEquals(Resource.error("Error",movieModel),returnedValue)
    }
}