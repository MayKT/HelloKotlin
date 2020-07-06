package com.mkttestprojects.hellokotlin.models.base

class Resource<T>(val status: Status, val data: T, val message: String) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, "")
        }

        fun <T> error(msg: String, data: T): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T): Resource<T>? {
            return Resource(Status.LOADING, data, "")
        }
    }


    override fun equals(other: Any?): Boolean {

        if (other == null)
            return false


        if (javaClass != other.javaClass)
            return false

        val resource = other as Resource<T>

        if (this.status != resource.status)
            return false

        if (this.data != null)
            if (this.data != resource.data)
                return false

        if (this.message != resource.message)
            return false

        return true
    }

}
