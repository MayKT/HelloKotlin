package com.mkttestprojects.hellokotlin.models

import com.mkttestprojects.hellokotlin.common.Pageable
import java.io.Serializable
import androidx.room.Ignore

class MovieListInfo : Serializable, Pageable {

    constructor()

    @Ignore
    constructor(
        poster_path: String,
        adult: Boolean,
        overview: String,
        release_date: String,
        id: Int,
        original_title: String,
        original_language: String,
        title: String,
        backdrop_path: String,
        vote_count: Int,
        video: Boolean,
        runtime: Int
    ) {
        this.poster_path = poster_path
        this.adult = adult
        this.overview = overview
        this.release_date = release_date
        this.id = id
        this.original_title = original_title
        this.original_language = original_language
        this.title = title
        this.backdrop_path = backdrop_path
        this.vote_count = vote_count
        this.video = video
        this.runtime = runtime
    }


    var poster_path: String = ""
    var adult = false
    var overview: String = ""
    var release_date: String = ""
    var id = 0
    var original_title: String = ""
    var original_language: String = ""
    var title: String = ""
    var backdrop_path: String = ""
    var vote_count = 0
    var video = false
    var runtime = 0
}