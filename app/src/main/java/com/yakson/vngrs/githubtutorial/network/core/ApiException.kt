package com.yakson.vngrs.githubtutorial.network.core

/**
 * Exception Class that handles Api Exceptions
 */
data class ApiException(val status : ExceptionStatus, override var message: String?, val code : Int) : Exception(message) {

    /**
     * User Error for showing customizing error
     * Api Error for api errors
     */
    enum class ExceptionStatus {
        GENERAL_ERROR,
        API_ERROR
    }

}