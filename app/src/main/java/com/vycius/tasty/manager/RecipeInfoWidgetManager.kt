package com.vycius.tasty.manager

import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.vycius.tasty.App
import com.vycius.tasty.model.Recipe
import com.vycius.tasty.widget.RecipeInfoWidget
import javax.inject.Inject


class RecipeInfoWidgetManager(private val application: App) {

    @Inject
    lateinit var moshi: Moshi

    val jsonRecipeAdapter: JsonAdapter<Recipe> by lazy {
        moshi.adapter(Recipe::class.java)
    }

    val sharedPreferences: SharedPreferences by lazy {
        application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    init {
        application.component.inject(this)
    }

    @SuppressLint("CommitPrefEdits")
    fun updateWidgetRecipe(recipe: Recipe) {
        val recipeJson = jsonRecipeAdapter.toJson(recipe)

        sharedPreferences.edit().apply {
            putString(KEY_RECIPE, recipeJson)
            commit()
        }

        updateWidget()
    }

    private fun updateWidget() {
        val intent = Intent(application, RecipeInfoWidget::class.java)

        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE

        val componentName = ComponentName(application, RecipeInfoWidget::class.java)
        val appWidgetIds = AppWidgetManager.getInstance(application).getAppWidgetIds(componentName)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)

        application.sendBroadcast(intent)
    }

    fun getRecipe(): Recipe? {
        val recipe = sharedPreferences.getString(KEY_RECIPE, null)

        return recipe?.let { r -> jsonRecipeAdapter.fromJson(r) }
    }

    companion object {
        private const val PREFERENCES_NAME = "RecipeInfoWidget"
        private const val KEY_RECIPE = "Recipe"
    }

}