package com.yakson.vngrs.githubtutorial.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.yakson.vngrs.githubtutorial.utils.AppHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * The Main [Module] that holds all app core classes and provides these instances
 */
@Module
class AppModule {

    /**
     * Provides Application Context
     */
    @Singleton
    @Provides
    fun provideContext(application : Application) : Context {
        return application.applicationContext
    }

    /**
     * Provides [Gson] instance
     */
    @Provides
    fun provideGson() : Gson {
        return Gson()
    }

    /**
     * Provide [CompositeDisposable] instance
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    /**
     * Provides [AppHelper] instance
     */
    @Singleton
    @Provides
    fun provideAppHelper(context: Context) : AppHelper {
        return AppHelper(context)
    }

}