package com.yakson.vngrs.githubtutorial

import android.content.Context
import androidx.multidex.MultiDex
import com.squareup.leakcanary.LeakCanary
import com.yakson.vngrs.githubtutorial.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.paperdb.Paper

class GithubApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        initLeakCanary()
        initPaperDb()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    /**
     * Initialize Leak Canary for catch memory leak
     */
    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        try {
            LeakCanary.install(this)
        } catch (error: Exception) {
            error.printStackTrace()
        }
    }

    /**
     * Initialize the paper db
     */
    private fun initPaperDb() {
        Paper.init(this)
    }
}