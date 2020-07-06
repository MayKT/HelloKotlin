package com.mkttestprojects.hellokotlin

import com.mkttestprojects.hellokotlin.models.DetailMovieListModel
import com.mkttestprojects.hellokotlin.models.MovieListInfo
import com.mkttestprojects.hellokotlin.models.MovieListModel
import java.util.*

object TestUtil {

    val TEST_MOVIE_LIST_INFO: List<MovieListInfo> =
        Collections.unmodifiableList(
            object : ArrayList<MovieListInfo>() {
                init {
                    add(
                        MovieListInfo(
                            "Poster_path",
                            false,
                            "overview",
                            "release_date",
                            10,
                            "o_title",
                            "o_language",
                            "title",
                            "backdrop",
                            1,
                            false,
                            1
                        )
                    )
                }
            }).toList()

    val TEST_MOVIE_LIST_MODEL: MovieListModel = MovieListModel(1, 1, 1, TEST_MOVIE_LIST_INFO)

    val TEST_MOVIE_DETAIL_MODEL = DetailMovieListModel(true,"backdrop_path",1,"o_language","o_title","overview","release_date",60,"title","poster_path")
}