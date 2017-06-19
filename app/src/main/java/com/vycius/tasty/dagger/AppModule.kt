package com.vycius.tasty.dagger

import android.app.Application
import com.vycius.tasty.App
import com.vycius.tasty.service.RecipesService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class AppModule constructor(private val application: App) {

    @Provides
    @Singleton
    internal fun providesApplication(): Application = application

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun providesRecipeService(retrofit: Retrofit): RecipesService {
        return retrofit.create<RecipesService>(RecipesService::class.java)
    }

    companion object {
        val API_BASE_URL = "http://go.udacity.com/"
    }
}