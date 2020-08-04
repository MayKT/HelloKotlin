package com.mkttestprojects.hellokotlin.ui.movietrailer

import com.mkttestprojects.hellokotlin.LiveDataTestUtil
import com.mkttestprojects.hellokotlin.TestUtil
import com.mkttestprojects.hellokotlin.models.MovieTrailerModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.repository.movietrailer.MovieTrailerRepository
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


@ExtendWith(InstantExecutorExtension ::class)
class MovieTrailerViewModelTest {

    @Mock
    lateinit var movieTrailerRepository: MovieTrailerRepository

    lateinit var movieTrailerViewModel: MovieTrailerViewModel

    @BeforeEach
    fun init(){
        MockitoAnnotations.initMocks(this)
        movieTrailerViewModel = MovieTrailerViewModel(movieTrailerRepository)
    }

    @Test
    internal fun observeMovieTrailer_returnSuccess() {
        val trailerModel = TestUtil.TEST_TRAILER_MODEL
        val liveDataTestUtil : LiveDataTestUtil<Resource<MovieTrailerModel>> = LiveDataTestUtil()
        val returnedData : Flowable<Resource<MovieTrailerModel>> = SingleToFlowable.just(Resource.success(trailerModel))

        Mockito.`when`(movieTrailerRepository.getMovieTrailerById(anyInt())).thenReturn(returnedData)

        val returnedValue : Resource<MovieTrailerModel> = liveDataTestUtil.getValue(movieTrailerViewModel.observeMovieTrailer(
            anyInt()))

        Assertions.assertEquals(Resource.success(trailerModel),returnedValue)
    }

    @Test
    internal fun observeMovieTrailer_returnFailure() {
        val trailerModel = MovieTrailerModel()
        val liveDataTestUtil : LiveDataTestUtil<Resource<MovieTrailerModel>> = LiveDataTestUtil()
        val returnedData : Flowable<Resource<MovieTrailerModel>> = SingleToFlowable.just(Resource.error("Error",trailerModel))

        Mockito.`when`(movieTrailerRepository.getMovieTrailerById(anyInt())).thenReturn(returnedData)

        val returnedValue : Resource<MovieTrailerModel> = liveDataTestUtil.getValue(movieTrailerViewModel.observeMovieTrailer(
            anyInt()))

        Assertions.assertEquals(Resource.error("Error",trailerModel),returnedValue)    }
}