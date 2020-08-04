package com.mkttestprojects.hellokotlin.repository

import com.mkttestprojects.hellokotlin.TestUtil
import com.mkttestprojects.hellokotlin.models.MovieTrailerInfoModel
import com.mkttestprojects.hellokotlin.models.MovieTrailerModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.network.MovieTrailerApi
import com.mkttestprojects.hellokotlin.repository.movietrailer.MovieTrailerRepository
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


@ExtendWith(InstantExecutorExtension ::class)
class MovieTrailerRepositoryTest {

    @Mock
    lateinit var movieTrailerApi: MovieTrailerApi

    lateinit var movieTrailerRepository: MovieTrailerRepository

    @BeforeEach
    fun initBeforEach(){
        MockitoAnnotations.initMocks(this)
        movieTrailerRepository = MovieTrailerRepository(movieTrailerApi)
    }

    @Test
    internal fun observeMovieTrailer_returnSuccess() {
        val trailerModel = TestUtil.TEST_TRAILER_MODEL
        Mockito.`when`(movieTrailerApi.getMovieTrailer(anyInt(), anyString())).thenReturn(Flowable.just(trailerModel))

        val returnedValue = movieTrailerRepository.getMovieTrailerById(eq(anyInt())).blockingFirst()

        assertEquals(Resource.success(trailerModel),returnedValue)
    }

    @Test
    internal fun observeMovieTrailer_returnFailure() {
        val trailerModel = MovieTrailerModel()
        Mockito.`when`(movieTrailerApi.getMovieTrailer(anyInt(), anyString())).thenReturn(Flowable.just(trailerModel))

        val returnedValue = movieTrailerRepository.getMovieTrailerById(eq(anyInt())).blockingFirst()

        assertEquals(Resource.error("Error",null),returnedValue)

    }

//    @Test
//    internal fun observeMovieTrailer_returnEmptyResults() {
//        val trailerModel = TestUtil.TEST_TRAILER_MODEL
//        trailerModel.results = ArrayList<MovieTrailerInfoModel>()
////        val liveDataTestUtil = LiveDataTestUtil<MovieTrailerModel>()
////        val returnedData = MutableLiveData<MovieTrailerModel>()
////        returnedData.value = trailerModel
////        val returnedData = Resource.error("Error",trailerModel)
//
//        Mockito.`when`(movieTrailerApi.getMovieTrailer(anyInt(), anyString())).thenReturn(Flowable.just(trailerModel))
//
//        val returnedValue =
//            movieTrailerRepository.getMovieTrailerById(eq(anyInt())).blockingFirst()
//
//        assertEquals(returnedValue.data.results.size,0)
//    }
}