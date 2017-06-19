package com.vycius.tasty.dagger

import com.vycius.tasty.MainActivityFragment
import com.vycius.tasty.image.GlideConfiguration
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivityFragment: MainActivityFragment)
    fun inject(glideConfiguration: GlideConfiguration)
}