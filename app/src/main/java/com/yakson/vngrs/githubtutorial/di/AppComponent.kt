package com.yakson.vngrs.githubtutorial.di

import android.app.Application
import com.yakson.vngrs.githubtutorial.GithubApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ViewModelBindingsModule::class, AppModule::class,
        ApiModule::class, ActivityBindingsModule::class]
)
interface AppComponent : AndroidInjector<GithubApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}