package com.vycius.tasty

import android.app.Application
import android.content.Context
import com.vycius.tasty.dagger.AppComponent
import com.vycius.tasty.dagger.AppModule
import com.vycius.tasty.dagger.DaggerAppComponent


class App : Application() {

    lateinit var component: AppComponent


    override fun onCreate() {
        super.onCreate()

        inject()
    }

    private fun inject() {
        component = buildComponent()
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }



    companion object {
        fun get(context: Context): App {
            return context.applicationContext as App
        }
    }

}