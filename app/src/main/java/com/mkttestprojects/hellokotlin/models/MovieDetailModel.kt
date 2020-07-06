package com.mkttestprojects.hellokotlin.models

import java.io.Serializable

class MovieDetailModel : Serializable {
    var isAdult = false
    var backdrop_path: String? = null
    var budget = 0
    var homepage: String? = null
    var id = 0
    var imdb_id: String? = null
    var original_language: String? = null
    var original_title: String? = null
    var overview: String? = null
    var popularity = 0
    var poster_path: String? = null
    var release_date: String? = null
    var revenue = 0
    var runtime = 0
    var status: String? = null
    var title: String? = null
    var isVideo = false
    var vote_average = 0
    var vote_count = 0

}