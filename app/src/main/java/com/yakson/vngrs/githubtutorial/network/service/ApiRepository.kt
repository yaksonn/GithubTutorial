package com.yakson.vngrs.githubtutorial.network.service


import com.yakson.vngrs.githubtutorial.model.Item
import com.yakson.vngrs.githubtutorial.model.Owner
import com.yakson.vngrs.githubtutorial.model.SearchRepositoriesModel
import io.reactivex.Single

interface ApiRepository {
    fun getRepos(data: Pair<String?, HashMap<String, Any>>?): Single<SearchRepositoriesModel>

    fun getUserDetail(url: String?): Single<Owner>

    fun getOtherReposDetail(url: String?): Single<List<Item>>
}