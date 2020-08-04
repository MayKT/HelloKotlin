package com.mkttestprojects.hellokotlin.ui.main.home

import com.mkttestprojects.hellokotlin.LiveDataTestUtil
import com.mkttestprojects.hellokotlin.TestUtil
import com.mkttestprojects.hellokotlin.models.MovieListInfo
import com.mkttestprojects.hellokotlin.models.MovieListModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.repository.main.home.HomeRepository
import com.mkttestprojects.hellokotlin.util.InstantExecutorExtension
import io.reactivex.Flowable
import io.reactivex.internal.operators.single.SingleToFlowable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExtendWith(InstantExecutorExtension ::class)
class HomeViewModelTest {

    @Mock
    lateinit var homeRepository: HomeRepository

    lateinit var homeViewModel : HomeViewModel

    @BeforeEach
    fun initBeforeEach() {
        MockitoAnnotations.initMocks(this)
        homeViewModel = HomeViewModel(homeRepository)
    }

    @Test
    internal fun observeMovies_returnMovies() {
        val movieListModel : MovieListModel = TestUtil.TEST_MOVIE_LIST_MODEL
        val liveDataTestUtil: LiveDataTestUtil<Resource<MovieListModel>> = LiveDataTestUtil()
        val returnedData: Flowable<Resource<MovieListModel>> =
            SingleToFlowable.just(Resource.success(movieListModel))
        Mockito.`when`(homeRepository.getNowPlayingMovies(1)).thenReturn(returnedData)

        val returnedValue: Resource<MovieListModel> = liveDataTestUtil.getValue(homeViewModel.observeMovies())

        Assertions.assertEquals(Resource.success(movieListModel),returnedValue)
    }

    @Test
    internal fun observeMovies_returnEmptyResult() {
        val movieListModel : MovieListModel = TestUtil.TEST_MOVIE_LIST_MODEL
        movieListModel.results = ArrayList<MovieListInfo>()
        val liveDataTestUtil: LiveDataTestUtil<Resource<MovieListModel>> = LiveDataTestUtil()
        val returnedData: Flowable<Resource<MovieListModel>> =
            SingleToFlowable.just(Resource.error("Error",movieListModel))
        Mockito.`when`(homeRepository.getNowPlayingMovies(1)).thenReturn(returnedData)

        val returnedValue: Resource<MovieListModel> = liveDataTestUtil.getValue(homeViewModel.observeMovies())

        Assertions.assertEquals(Resource.error("Error",movieListModel),returnedValue)    }
}