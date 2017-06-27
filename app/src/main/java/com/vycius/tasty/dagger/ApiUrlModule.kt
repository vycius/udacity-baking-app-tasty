package com.vycius.tasty.dagger

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ApiUrlModule {

    @Provides
    @Named("apiUrl")
    fun provideApiUrl(): String {
        return "http://go.udacity.com/"
    }
}