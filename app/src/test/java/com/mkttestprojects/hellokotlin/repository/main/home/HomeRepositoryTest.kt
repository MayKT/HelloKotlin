package com.mkttestprojects.hellokotlin.repository.main.home

import com.mkttestprojects.hellokotlin.TestUtil
import com.mkttestprojects.hellokotlin.models.MovieListInfo
import com.mkttestprojects.hellokotlin.models.MovieListModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.network.HomeApi
import com.mkttestprojects.hellokotlin.repository.main.home.HomeRepository
import com.mkttestprojects.hellokotlin.util.InstantExecutorExtension
import io.reactivex.Flowable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExtendWith(InstantExecutorExtension::class)
class HomeRepositoryTest {

    @Mock
    lateinit var homeApi: HomeApi

    lateinit var homeRepository: HomeRepository

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        homeRepository = HomeRepository(homeApi)
    }

    /*
        observe movies return success
     */
    @Test
    internal fun observeMovies_returnSuccess() {
    // Arrange
        val movielistModel = TestUtil.TEST_MOVIE_LIST_MODEL
        Mockito.`when`(homeApi.getNowPlayingMovies(anyString(), anyString(), anyInt())).thenReturn(
            Flowable.just(movielistModel))

    // Act
        val returnedValue = homeRepository.getNowPlayingMovies(eq(eq(anyInt()))).blockingFirst()

    // Assert
        assertEquals(Resource.success(movielistModel),returnedValue)
    }

    /*
        observe movies return empty result[]
     */
    @Test
    internal fun observeMovies_returnFailure() {
        // Arrange
        val movielistModel = MovieListModel()
        movielistModel.results = ArrayList<MovieListInfo>()
        Mockito.`when`(
            homeApi.getNowPlayingMovies(anyString(), anyString(), anyInt())
        )
            .thenReturn(Flowable.just(movielistModel))


        // Act
        val returnedValue = homeRepository.getNowPlayingMovies(eq(eq(anyInt()))).blockingFirst()

        // Assert
        assertEquals(Resource.error("Error",null),returnedValue)
    }

}