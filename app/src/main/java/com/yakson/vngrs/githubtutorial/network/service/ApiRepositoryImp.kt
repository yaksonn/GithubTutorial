package com.yakson.vngrs.githubtutorial.network.service

import com.yakson.vngrs.githubtutorial.R
import com.yakson.vngrs.githubtutorial.model.Item
import com.yakson.vngrs.githubtutorial.model.Owner
import com.yakson.vngrs.githubtutorial.model.SearchRepositoriesModel
import com.yakson.vngrs.githubtutorial.network.core.GenericResponse
import com.yakson.vngrs.githubtutorial.utils.AppHelper
import io.reactivex.Single
import retrofit2.Response

class ApiRepositoryImp constructor(private val apiService: ApiServiceInterface, private val appHelper: AppHelper) :
    ApiRepository {

    override  fun getRepos(data: Pair<String?, HashMap<String, Any>>?): Single<SearchRepositoriesModel> {
        return apiService.getRepos(data?.second).flatMap { interceptRawError(it) }
    }

    override fun getUserDetail(url: String?): Single<Owner> {
        return apiService.getUserDetail(url).flatMap { interceptRawError(it) }
    }

    override fun getOtherReposDetail(url: String?): Single<List<Item>> {
        return apiService.getOtherReposDetail(url).flatMap { interceptRawError(it) }
    }

    /**
     * Filter service errors with [GenericResponse] body
     */
    private fun <T> interceptRawError(response: Response<T>): Single<T?> {
        val requestCode = response.code()
        if (requestCode == 401) {
            // Returns a singleton instance of a never-signalling Single (only calls onSubscribe).
            return Single.never()
        } else if (requestCode in 400..501) {
            return Single.error(Throwable(appHelper.getContext().getString(R.string.api_exception_technical_error)))
        }
        return if (requestCode == 200 && response.isSuccessful) {

            if (response.body() == null) {
                Single.error(Throwable(appHelper.getContext().getString(R.string.api_exception_unknown)))
            } else {
                Single.just(response.body())
            }
        } else Single.just(response.body())
    }
}