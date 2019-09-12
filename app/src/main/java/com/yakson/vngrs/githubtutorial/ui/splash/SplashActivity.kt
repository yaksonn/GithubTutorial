package com.yakson.vngrs.githubtutorial.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import com.yakson.vngrs.githubtutorial.R
import com.yakson.vngrs.githubtutorial.databinding.ActivityRepositoryDetailBinding
import com.yakson.vngrs.githubtutorial.ui.BaseActivity
import com.yakson.vngrs.githubtutorial.ui.main.MainActivity
import com.yakson.vngrs.githubtutorial.ui.repositorydetail.RepositoryDetailViewModel
import kotlin.reflect.KClass

class SplashActivity : BaseActivity<RepositoryDetailViewModel, ActivityRepositoryDetailBinding>() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000

    override fun getViewModelClass(): KClass<RepositoryDetailViewModel> {
        return RepositoryDetailViewModel::class
    }

    override fun getLayoutId(): Int {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_splash
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    public override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }
}





