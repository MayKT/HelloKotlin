package com.mkttestprojects.hellokotlin

import com.mkttestprojects.hellokotlin.models.*
import java.util.*

object TestUtil {

    val TEST_MOVIE_LIST_INFO: List<MovieListInfo> =
        Collections.unmodifiableList(
            object : ArrayList<MovieListInfo>() {
                init {
                    add(
                        MovieListInfo(
                            "/8WUVHemHFH2ZIP6NWkwlHWsyrEL.jpg",
                            false,
                            "After he and his wife are murdered, marine Ray Garrison is resurrected by a team of scientists. Enhanced with nanotechnology, he becomes a superhuman, biotech killing machine—'Bloodshot'. As Ray first trains with fellow super-soldiers, he cannot recall anything from his former life. But when his memories flood back and he remembers the man that killed both him and his wife, he breaks out of the facility to get revenge, only to discover that there's more to the conspiracy than he thought.",
                            "2020-03-05",
                            338762,
                            "Bloodshot",
                            "en",
                            "Bloodshot",
                            "/lP5eKh8WOcPysfELrUpGhHJGZEH.jpg",
                            2758,
                            false,
                            110
                        )
                    )
                }
            }).toList()

    val TEST_MOVIE_LIST_MODEL: MovieListModel = MovieListModel(1, 1, 1, TEST_MOVIE_LIST_INFO)

    val TEST_MOVIE_DETAIL_MODEL = DetailMovieListModel(
        true,
        "backdrop_path",
        1,
        "o_language",
        "o_title",
        "overview",
        "release_date",
        60,
        "title",
        "poster_path"
    )

    val TEST_TRAILER_INFO_MODEL = Collections.unmodifiableList(
        object : ArrayList<MovieTrailerInfoModel>() {
            init {
                add(
                    MovieTrailerInfoModel(
                        "5dad5e3afea6e30011ada0ab", "F95Fk255I4M", "BLOODSHOT – International Trailer", "YouTube", 1080, "Trailer"
                    )
                )
            }
        }).toList()

    val TEST_TRAILER_MODEL = MovieTrailerModel(338726, TEST_TRAILER_INFO_MODEL)
}