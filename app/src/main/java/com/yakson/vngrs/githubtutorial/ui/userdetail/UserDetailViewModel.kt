package com.yakson.vngrs.githubtutorial.ui.userdetail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.yakson.vngrs.githubtutorial.model.Item
import com.yakson.vngrs.githubtutorial.model.Owner
import com.yakson.vngrs.githubtutorial.network.core.ApiException
import com.yakson.vngrs.githubtutorial.network.service.ApiRepositoryImp
import com.yakson.vngrs.githubtutorial.ui.base.BaseViewModel
import com.yakson.vngrs.githubtutorial.utils.AppHelper
import com.yakson.vngrs.githubtutorial.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(
    private val mApiImp: ApiRepositoryImp,
    private val appHelper: AppHelper,
    private val userDetailUseCase: UserDetailUseCase,
    private val userDetailOtherRepoUseCase: UserDetailOtherRepoUseCase,
    private val compositeDisposable: CompositeDisposable
) : BaseViewModel(appHelper, compositeDisposable) {

    private val userDetail: MutableLiveData<Owner?> = MutableLiveData()
    private val userDetailOtherRepo: MutableLiveData<List<Item>> = MutableLiveData()

    private fun testLog() {
        LogUtils.d("test user detail view model")
        LogUtils.d(mApiImp.toString())
    }

    init {
        testLog()
    }

    fun fetchUserDetail(url: String) {
        if (!isConnected()) {
            return
        }
        setNetworkStatus(NetworkState.LOADING)
        val disposable = userDetailUseCase.execute(object : DisposableSingleObserver<Owner>() {
                override fun onSuccess(t: Owner) {
                    userDetail.value = t
                }
                override fun onError(e: Throwable) {
                    setNetworkStatus(NetworkState.FAILED)
                }
            }, url)
        compositeDisposable.add(disposable)
    }

    /**
     * Return list as live data
     */
    fun getUserDetail(): LiveData<Owner?> {
        return userDetail
    }

    /**
     * Fetch UserDetail Other Repo Method
     */
    fun fetchUserDetailOtherRepo(url: String) {
        if (!isConnected()) {
            return
        }
        setNetworkStatus(NetworkState.LOADING)
        val disposable = userDetailOtherRepoUseCase.execute(object : DisposableSingleObserver<List<Item>>() {
            override fun onSuccess(t: List<Item>) {
                setNetworkStatus(NetworkState.LOADED)
                userDetailOtherRepo.value = t
            }

            override fun onError(e: Throwable) {
                setNetworkStatus(NetworkState.FAILED)
                if (e is ApiException) {
                    setError(e)
                }
            }
        }, url)
        compositeDisposable.add(disposable)
    }

    /**
     * Return userdetail repo status as live data
     */
    fun getUserDetailOtherRepo(): LiveData<List<Item>> {
        return userDetailOtherRepo
    }


}