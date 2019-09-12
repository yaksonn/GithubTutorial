package com.yakson.vngrs.githubtutorial.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.yakson.vngrs.githubtutorial.model.Item
import com.yakson.vngrs.githubtutorial.model.SearchRepositoriesModel
import com.yakson.vngrs.githubtutorial.network.core.ApiException
import com.yakson.vngrs.githubtutorial.network.service.ApiRepositoryImp
import com.yakson.vngrs.githubtutorial.ui.base.BaseViewModel
import com.yakson.vngrs.githubtutorial.utils.AppHelper
import com.yakson.vngrs.githubtutorial.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mApiImp: ApiRepositoryImp,
    private val appHelper: AppHelper,
    private val mainUseCase: MainUseCase,
    private val compositeDisposable: CompositeDisposable
) : BaseViewModel(appHelper, compositeDisposable) {

    private val repoDataResponse: MutableLiveData<List<Item?>> = MutableLiveData()


    private fun testLog() {
        LogUtils.d("test main view model")
        LogUtils.d(mApiImp.toString())
    }

    init {
        testLog()
    }

    /**
     * Fetch Search Method
     */
    fun fetchRepo(query: String, perPage: Int, page: Int) {
        if (!isConnected()) {
            return
        }
        setNetworkStatus(NetworkState.LOADING)
        val disposable = mainUseCase.execute(object : DisposableSingleObserver<SearchRepositoriesModel>() {
            override fun onSuccess(t: SearchRepositoriesModel) {
                setNetworkStatus(NetworkState.LOADED)
                repoDataResponse.value = t.items
            }

            override fun onError(e: Throwable) {
                setNetworkStatus(NetworkState.FAILED)
                if (e is ApiException) {
                    setError(e)
                }
            }
        }, Pair("", object : HashMap<String, Any>() {
            init {
                put("q", query)
                put("per_page", perPage)
                put("page", page)
            }
        }))
        compositeDisposable.add(disposable)
    }

    /**
     * Return search repo status as live data
     */
    fun getRepoData(): LiveData<List<Item?>> {
        return repoDataResponse
    }

}