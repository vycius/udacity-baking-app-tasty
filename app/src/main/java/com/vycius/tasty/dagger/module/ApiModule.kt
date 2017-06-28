package com.vycius.tasty.dagger.module

import com.squareup.moshi.Moshi
import com.vycius.tasty.service.RecipesService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApiModule(private val apiUrl: String) {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun providesRecipeService(retrofit: Retrofit): RecipesService {
        return retrofit.create(RecipesService::class.java)
    }
}