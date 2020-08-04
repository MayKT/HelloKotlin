package com.mkttestprojects.hellokotlin.models

class MovieTrailerInfoModel () {

    constructor(id: String, key: String, name: String, site: String, size: Int, type: String) : this() {
        this.id = id
        this.key = key
        this.name = name
        this.site = site
        this.size = size
        this.type = type
    }

    var id: String = ""
    var key: String = ""
    var name: String = ""
    var site: String = ""
    var size = 0
    var type: String = ""
}