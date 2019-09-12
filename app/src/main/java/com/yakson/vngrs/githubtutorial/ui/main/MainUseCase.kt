package com.yakson.vngrs.githubtutorial.ui.main


import com.yakson.vngrs.githubtutorial.model.SearchRepositoriesModel
import com.yakson.vngrs.githubtutorial.network.core.UseCase
import com.yakson.vngrs.githubtutorial.network.service.ApiRepositoryImp
import io.reactivex.Single
import javax.inject.Inject

class MainUseCase @Inject constructor(private val mApiImp: ApiRepositoryImp) :
    UseCase<SearchRepositoriesModel, Pair<String, HashMap<String, Any>>>() {


    override fun buildUseCaseObservable(params: Pair<String, HashMap<String, Any>>?): Single<SearchRepositoriesModel> {
        return mApiImp.getRepos(params).flatMap { model ->
            Single.just(model)
        }
    }

}
