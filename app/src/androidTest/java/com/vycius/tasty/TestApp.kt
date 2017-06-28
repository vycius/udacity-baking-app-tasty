package com.vycius.tasty

import com.vycius.tasty.dagger.AppComponent
import com.vycius.tasty.dagger.AppModule
import com.vycius.tasty.dagger.DaggerTestAppComponent


class TestApp : App() {

    override fun buildComponent(): AppComponent {
        return DaggerTestAppComponent.builder().appModule(AppModule(this)).build()
    }
}