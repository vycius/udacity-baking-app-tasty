package com.vycius.tasty.dagger

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, TestApiUrlModule::class))
interface TestAppComponent : AppComponent