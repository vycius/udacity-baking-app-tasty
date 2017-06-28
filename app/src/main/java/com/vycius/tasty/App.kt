package com.vycius.tasty

import android.app.Application
import android.content.Context
import com.vycius.tasty.dagger.AppComponent
import com.vycius.tasty.dagger.DaggerAppComponent
import com.vycius.tasty.dagger.module.ApiModule
import com.vycius.tasty.dagger.module.AppModule


open class App : Application() {

    lateinit var component: AppComponent


    override fun onCreate() {
        super.onCreate()

        inject()
    }

    private fun inject() {
        component = buildComponent()
    }

    protected open fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .apiModule(ApiModule(API_URL))
                .build()
    }

    companion object {

        protected val API_URL = "http://go.udacity.com/"

        fun get(context: Context): App {
            return context.applicationContext as App
        }
    }

}