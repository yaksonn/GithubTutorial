package com.yakson.vngrs.githubtutorial.ui.userdetail

import com.yakson.vngrs.githubtutorial.model.Owner
import com.yakson.vngrs.githubtutorial.network.core.UseCase
import com.yakson.vngrs.githubtutorial.network.service.ApiRepositoryImp
import io.reactivex.Single
import javax.inject.Inject

class UserDetailUseCase @Inject constructor(private val mApiImp: ApiRepositoryImp) :
    UseCase<Owner, String>() {

    override fun buildUseCaseObservable(params: String?): Single<Owner> {
        return mApiImp.getUserDetail(params!!).flatMap { model ->
            Single.just(model)
        }
    }
}