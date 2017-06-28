package com.vycius.tasty

import com.vycius.tasty.dagger.AppComponent
import com.vycius.tasty.dagger.DaggerAppComponent
import com.vycius.tasty.dagger.module.ApiModule
import com.vycius.tasty.dagger.module.AppModule


class TestApp : App() {

    override fun buildComponent(): AppComponent {
        return buildComponentWithApiUrl("/")
    }

    private fun buildComponentWithApiUrl(apiUrl: String): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .apiModule(ApiModule(apiUrl))
                .build()
    }

    fun injectWithApiUrl(apiUrl: String) {
        component = buildComponentWithApiUrl(apiUrl)
    }
}