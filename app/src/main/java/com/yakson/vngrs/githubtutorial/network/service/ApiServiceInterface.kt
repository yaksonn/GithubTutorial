package com.yakson.vngrs.githubtutorial.network.service

import com.yakson.vngrs.githubtutorial.model.Item
import com.yakson.vngrs.githubtutorial.model.Owner
import com.yakson.vngrs.githubtutorial.model.SearchRepositoriesModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiServiceInterface {

    @GET("search/repositories?")
    fun getRepos(@QueryMap params: HashMap<String, Any>?): Single<Response<SearchRepositoriesModel>>

    @GET
    fun getUserDetail(@Url url: String?): Single<Response<Owner>>

    @GET
    fun getOtherReposDetail(@Url url: String?): Single<Response<List<Item>>>
}