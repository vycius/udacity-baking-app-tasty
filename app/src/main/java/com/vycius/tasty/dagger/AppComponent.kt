package com.vycius.tasty.dagger

import com.vycius.tasty.MainActivityFragment
import com.vycius.tasty.RecipeDetailActivity
import com.vycius.tasty.image.GlideConfiguration
import com.vycius.tasty.manager.RecipeInfoWidgetManager
import com.vycius.tasty.service.RecipeInfoWidgetRemoteViewService
import com.vycius.tasty.widget.RecipeInfoWidget
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivityFragment: MainActivityFragment)
    fun inject(glideConfiguration: GlideConfiguration)
    fun inject(recipeInfoWidgetManager: RecipeInfoWidgetManager)
    fun inject(recipeInfoWidgetRemoteViewService: RecipeInfoWidgetRemoteViewService)
    fun inject(recipeInfoWidget: RecipeInfoWidget)
    fun inject(recipeDetailActivity: RecipeDetailActivity)
}