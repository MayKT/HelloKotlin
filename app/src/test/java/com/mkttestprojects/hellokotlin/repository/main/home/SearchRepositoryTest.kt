package com.mkttestprojects.hellokotlin.repository.main.home

import com.mkttestprojects.hellokotlin.TestUtil
import com.mkttestprojects.hellokotlin.models.MovieListInfo
import com.mkttestprojects.hellokotlin.models.MovieListModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.network.SearchApi
import com.mkttestprojects.hellokotlin.repository.main.home.SearchRepository
import com.mkttestprojects.hellokotlin.util.InstantExecutorExtension
import io.reactivex.Flowable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExtendWith(InstantExecutorExtension ::class)
class SearchRepositoryTest {

    @Mock
    lateinit var searchApi: SearchApi

    lateinit var searchRepository: SearchRepository

    @BeforeEach
    fun initBeforeEach(){
        MockitoAnnotations.initMocks(this)
        searchRepository = SearchRepository(searchApi)
    }

    /*
    observe search results
    return success
     */
    @Test
    internal fun observeSearchResults_returnSuccess() {
        val movieListModel = TestUtil.TEST_MOVIE_LIST_MODEL
        Mockito.`when`(searchApi.getSearchResults(anyString(), anyString(), anyString(), anyInt()))
            .thenReturn(Flowable.just(movieListModel))

        val returnedData = searchRepository.getSearchResults(eq(eq(anyInt())).toString(), anyInt()).blockingFirst()

        Assertions.assertEquals(Resource.success(movieListModel),returnedData)
    }

    /*
        observe search results with null query
     */
    @Test
    @Throws(Exception ::class)
    internal fun observeSearchResult_withNullQuery_throwsException()  {

    }

    /*
        observe search results
        return failure
         */
    @Test
    internal fun observeSearchResult_returnFailure() {
        val movieListModel = MovieListModel()
        movieListModel.results = ArrayList<MovieListInfo>()
        Mockito.`when`(searchApi.getSearchResults(anyString(), anyString(), anyString(), anyInt()))
            .thenReturn(Flowable.just(movieListModel))

        val returnedData = searchRepository.getSearchResults(eq(eq(anyInt())).toString(), anyInt()).blockingFirst()

        Assertions.assertEquals(Resource.error("Error",null),returnedData)    }

    @Test
    internal fun observeSearchResults_returnError_throwsException() {
        Mockito.`when`(searchApi.getSearchResults(anyString(), anyString(), anyString(), anyInt()))
            .thenReturn(Flowable.error(Throwable("Error")))

        val returnedData = searchRepository.getSearchResults(eq(eq(anyInt())).toString(), anyInt()).blockingFirst()

        Assertions.assertEquals("Error",returnedData.message)     }
}