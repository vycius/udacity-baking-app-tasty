package com.vycius.tasty.dagger

import com.squareup.moshi.Moshi
import com.vycius.tasty.App
import com.vycius.tasty.manager.RecipeInfoWidgetManager
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
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .build()
    }


    @Provides
    @Singleton
    fun prodicesMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun providesRecipeService(retrofit: Retrofit): RecipesService {
        return retrofit.create<RecipesService>(RecipesService::class.java)
    }

    @Provides
    @Singleton
    fun procidesRecipeInfoWidgetManager(): RecipeInfoWidgetManager {
        return RecipeInfoWidgetManager(application)
    }


    companion object {
        val API_BASE_URL = "http://go.udacity.com/"
    }
}