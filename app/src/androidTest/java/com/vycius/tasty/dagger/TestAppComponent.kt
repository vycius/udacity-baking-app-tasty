package com.vycius.tasty.dagger

import com.vycius.tasty.dagger.module.ApiModule
import com.vycius.tasty.dagger.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class))
interface TestAppComponent : AppComponent