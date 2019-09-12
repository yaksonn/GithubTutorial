package com.yakson.vngrs.githubtutorial.di

import com.yakson.vngrs.githubtutorial.ui.main.MainActivity
import com.yakson.vngrs.githubtutorial.di.scope.ActivityScope
import com.yakson.vngrs.githubtutorial.ui.repositorydetail.RepositoryDetailActivity
import com.yakson.vngrs.githubtutorial.ui.splash.SplashActivity
import com.yakson.vngrs.githubtutorial.ui.userdetail.UserDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun provideSplashActivityInjector(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun provideMainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun provideRepositoryDetailActivityInjector(): RepositoryDetailActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun provideRUserDetailActivityInjector(): UserDetailActivity

}