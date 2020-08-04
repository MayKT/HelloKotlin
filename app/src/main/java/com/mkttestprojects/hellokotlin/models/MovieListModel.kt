package com.mkttestprojects.hellokotlin.models

import androidx.room.Ignore
import com.mkttestprojects.hellokotlin.common.Pageable
import java.io.Serializable

class MovieListModel (): Serializable,Pageable {

    @Ignore
    constructor(page: Int, total_results: Int, total_pages: Int, results: List<MovieListInfo>) :this(){
        this.page = page
        this.total_results = total_results
        this.total_pages = total_pages
        this.results = results
    }

    var page = 0
    var total_results = 0
    var total_pages = 0
    var results: List<MovieListInfo> = emptyList()
}