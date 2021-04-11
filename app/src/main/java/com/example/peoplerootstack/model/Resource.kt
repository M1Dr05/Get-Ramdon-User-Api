package com.example.peoplerootstack.model

class Resource<out T> private constructor(val status: Status, val data: T?, val error: Error?) {

    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Error?): Resource<T> {
            return Resource(Status.ERROR, null, error)
        }

    }
}