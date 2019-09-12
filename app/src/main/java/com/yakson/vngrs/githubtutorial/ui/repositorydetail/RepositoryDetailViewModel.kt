package com.yakson.vngrs.githubtutorial.ui.repositorydetail


import com.blankj.utilcode.util.LogUtils
import com.yakson.vngrs.githubtutorial.network.service.ApiRepositoryImp
import com.yakson.vngrs.githubtutorial.ui.base.BaseViewModel
import com.yakson.vngrs.githubtutorial.utils.AppHelper
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RepositoryDetailViewModel @Inject constructor(
    private val mApiImp: ApiRepositoryImp,
    private val appHelper: AppHelper,
    private val compositeDisposable: CompositeDisposable
) : BaseViewModel(appHelper, compositeDisposable) {


    private fun testLog() {
        LogUtils.d("test repository detail view model")
        LogUtils.d(mApiImp.toString())
    }

    init {
        testLog()
    }

}