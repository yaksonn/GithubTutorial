package com.yakson.vngrs.githubtutorial.ui.splash

import android.os.Bundle
import com.yakson.vngrs.githubtutorial.R
import com.yakson.vngrs.githubtutorial.databinding.ActivityRepositoryDetailBinding
import com.yakson.vngrs.githubtutorial.ui.BaseActivity
import com.yakson.vngrs.githubtutorial.ui.repositorydetail.RepositoryDetailViewModel
import kotlin.reflect.KClass

class SplashActivity : BaseActivity<RepositoryDetailViewModel, ActivityRepositoryDetailBinding>() {

    override fun getViewModelClass(): KClass<RepositoryDetailViewModel> {
        return RepositoryDetailViewModel::class
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_repository_detail
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}