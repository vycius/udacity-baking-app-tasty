package com.vycius.tasty.dagger.module

import com.squareup.moshi.Moshi
import com.vycius.tasty.App
import com.vycius.tasty.manager.RecipeInfoWidgetManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Module
class AppModule(private val application: App) {

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
    fun procidesRecipeInfoWidgetManager(): RecipeInfoWidgetManager {
        return RecipeInfoWidgetManager(application)
    }
}