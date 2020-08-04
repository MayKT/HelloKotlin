package com.mkttestprojects.hellokotlin.models

class MovieTrailerModel (){

    constructor(id: Int, results: List<MovieTrailerInfoModel>) : this() {
        this.id = id
        this.results = results
    }

    var id = 0
    var results: List<MovieTrailerInfoModel> = emptyList()
}