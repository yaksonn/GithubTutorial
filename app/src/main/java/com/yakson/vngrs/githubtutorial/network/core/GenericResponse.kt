package com.yakson.vngrs.githubtutorial.network.core

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Generic response class
 */
class GenericResponse<T> {

    @SerializedName("status")
    @Expose
    var status: String = ""
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("data")
    @Expose
    var data: T? = null

}
