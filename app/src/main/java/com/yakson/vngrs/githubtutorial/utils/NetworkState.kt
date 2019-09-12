package com.yakson.vngrs.githubtutorial.utils

import androidx.annotation.Nullable

/**
 * Class that hold network state
 */
class NetworkState(val status: Status, @Nullable message: String?) : Exception(message) {

    constructor(status: Status, message: String?, errorCode: Int) : this(status, message)

    enum class Status {
        LOADING,
        SUCCESS,
        FAILED
    }

    companion object {
        val LOADED = NetworkState(
            Status.SUCCESS,
            ""
        )
        val LOADING = NetworkState(
            Status.LOADING,
            ""
        )
        val FAILED = NetworkState(
            Status.FAILED,
            ""
            , 0
        )
    }
}