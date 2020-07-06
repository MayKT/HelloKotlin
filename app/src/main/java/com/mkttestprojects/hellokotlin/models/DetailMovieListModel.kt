package com.mkttestprojects.hellokotlin.models

import androidx.room.Ignore
import com.mkttestprojects.hellokotlin.common.Pageable
import java.io.Serializable

class DetailMovieListModel : Serializable,Pageable{

    constructor()

    @Ignore
    constructor(
        adult: Boolean,
        backdrop_path: String,
        id: Int,
        original_language: String,
        original_title: String,
        overview: String,
        release_date: String,
        runtime: Int,
        title: String,
        poster_path: String
    ) {
        this.adult = adult
        this.backdrop_path = backdrop_path
        this.id = id
        this.original_language = original_language
        this.original_title = original_title
        this.overview = overview
        this.release_date = release_date
        this.runtime = runtime
        this.title = title
        this.poster_path = poster_path
    }

    var adult : Boolean = false

    var backdrop_path : String = ""

    var id : Int = 0

    var original_language : String = ""

    var original_title : String = ""

    var overview : String = ""

    var release_date : String = ""

    var runtime : Int = 0

    var title : String = ""

    var poster_path : String = ""

}