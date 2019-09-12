package com.yakson.vngrs.githubtutorial.ui.userdetail

import com.yakson.vngrs.githubtutorial.model.Item
import com.yakson.vngrs.githubtutorial.network.core.UseCase
import com.yakson.vngrs.githubtutorial.network.service.ApiRepositoryImp
import io.reactivex.Single
import javax.inject.Inject

class UserDetailOtherRepoUseCase @Inject constructor(private val mApiImp: ApiRepositoryImp) :
    UseCase<List<Item>, String>() {

    override fun buildUseCaseObservable(params: String?): Single<List<Item>> {
        return mApiImp.getOtherReposDetail(params!!).flatMap { model ->
            Single.just(model)
        }
    }
}