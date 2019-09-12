package com.yakson.vngrs.githubtutorial.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yakson.vngrs.githubtutorial.di.scope.ViewModelKey
import com.yakson.vngrs.githubtutorial.ui.main.MainViewModel
import com.yakson.vngrs.githubtutorial.ui.repositorydetail.RepositoryDetailViewModel
import com.yakson.vngrs.githubtutorial.ui.splash.SplashViewModel
import com.yakson.vngrs.githubtutorial.ui.userdetail.UserDetailViewModel
import com.yakson.vngrs.githubtutorial.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelBindingsModule {

    /**
     * Returns an instance of [ViewModelFactory]
     */
    @Singleton
    @Binds
    abstract fun bindViewModelFactoryModule(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun provideSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryDetailViewModel::class)
    internal abstract fun provideRepositoryDetailViewModel(repositoryDetailViewModel: RepositoryDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    internal abstract fun provideUserDetailViewModel(userDetailViewModel: UserDetailViewModel): ViewModel

}