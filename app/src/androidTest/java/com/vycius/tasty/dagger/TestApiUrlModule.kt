package com.vycius.tasty.dagger

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class TestApiUrlModule {

    @Provides
    @Named("apiUrl")
    fun provideApiUrl(): String {
        return url
    }

    companion object {
        var url: String = ""
    }
}