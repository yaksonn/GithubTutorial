package com.yakson.vngrs.githubtutorial.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.NetworkUtils
import com.yakson.vngrs.githubtutorial.R
import com.yakson.vngrs.githubtutorial.network.core.ApiException
import com.yakson.vngrs.githubtutorial.utils.AppHelper
import com.yakson.vngrs.githubtutorial.utils.NetworkState
import com.yakson.vngrs.githubtutorial.utils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel constructor(
    private val appHelper: AppHelper,
    private val disposable: CompositeDisposable
) : ViewModel() {

    private val networkStatus: SingleLiveEvent<NetworkState>?
    private val error: SingleLiveEvent<ApiException>?

    fun isConnected(): Boolean {

        // Send message if connection not exist
        if (!NetworkUtils.isConnected()){
            setError(ApiException(ApiException.ExceptionStatus.API_ERROR, appHelper.getString(R.string.network_error), -1))
        }
        return NetworkUtils.isConnected()
    }

    init {
        networkStatus = SingleLiveEvent()
        error = SingleLiveEvent()
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    fun getNetworkStatus(): LiveData<NetworkState>? {
        return networkStatus
    }

    fun setNetworkStatus(networkState: NetworkState) {
        networkStatus?.value = networkState
    }

    fun getError(): LiveData<ApiException>? {
        return error
    }

    fun setError(apiException: ApiException) {
        error?.value = apiException
    }


}