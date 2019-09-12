package com.yakson.vngrs.githubtutorial.di

import com.google.gson.GsonBuilder
import com.yakson.vngrs.githubtutorial.BuildConfig
import com.yakson.vngrs.githubtutorial.network.service.ApiRepositoryImp
import com.yakson.vngrs.githubtutorial.network.service.ApiServiceInterface
import com.yakson.vngrs.githubtutorial.utils.AppHelper
import com.yakson.vngrs.githubtutorial.utils.BASE_URL
import com.yakson.vngrs.githubtutorial.utils.TIME_OUT
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * The Main [Module] that holds all api core classes and provides these instances
 */
@Module
class ApiModule {

    /**
     * Provides [GsonBuilder] instance
     */
    @Provides
    fun provideGSONBuilder(): GsonBuilder {
        return GsonBuilder()
    }

    /**
     * Provides [GsonConverterFactory] instance
     */
    @Provides
    fun provideConverterFactory(builder: GsonBuilder): Converter.Factory {
        return GsonConverterFactory.create(builder.create())
    }

    /**
     * Provides [RxJava2CallAdapterFactory] instance
     */
    @Provides
    fun provideRxConverterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    /**
     * Provides [HttpLoggingInterceptor] instance
     */
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

        return httpLoggingInterceptor
    }


    /**
     * Provides [Interceptor] for managing Authorization
     */
    @Provides
    @Named("AuthInterceptor")
    internal fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            var request = original.newBuilder().build()

            request = original.newBuilder()
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
    }

    /**
     * Provides [OkHttpClient] instance
     */
    @Provides
    fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor, @Named("AuthInterceptor") authInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor)
            .retryOnConnectionFailure(true)
            .build()
    }

    /**
     * Provides [Retrofit] instance
     */
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        gsonConverterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    /**
     * Provides [ApiService] instance
     */
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiServiceInterface {
        return retrofit.create(ApiServiceInterface::class.java)
    }

    /**
     * Provides [ApiHelper] instance
     */
    @Singleton
    @Provides
    fun provideApiRepositoryImp(apiService: ApiServiceInterface, appHelper: AppHelper): ApiRepositoryImp {
        return ApiRepositoryImp(apiService, appHelper)
    }
}