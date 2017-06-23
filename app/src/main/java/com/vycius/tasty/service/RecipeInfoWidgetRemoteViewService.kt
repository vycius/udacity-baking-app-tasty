package com.vycius.tasty.service

import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.vycius.tasty.App
import com.vycius.tasty.R
import com.vycius.tasty.manager.RecipeInfoWidgetManager
import com.vycius.tasty.model.Recipe
import javax.inject.Inject


class RecipeInfoWidgetRemoteViewService : RemoteViewsService() {

    @Inject
    lateinit var recipeInfoWidgetManager: RecipeInfoWidgetManager

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return object : RemoteViewsFactory {
            private var recipe: Recipe? = null

            override fun onCreate() {
                App.get(applicationContext).component.inject(this@RecipeInfoWidgetRemoteViewService)

            }

            override fun onDataSetChanged() {
                recipe = recipeInfoWidgetManager.getRecipe()
            }

            override fun onDestroy() {
            }

            override fun getCount(): Int {
                return recipe?.ingredients?.size ?: 0
            }

            override fun getViewAt(position: Int): RemoteViews? {
                val views = RemoteViews(packageName, R.layout.cell_wigdet_recipe_ingredient)

                val ingredient = recipe?.ingredients?.get(position)

                return ingredient?.let {
                    views.apply {
                        setTextViewText(R.id.recipe_ingredient_name, ingredient.ingredient)
                        setTextViewText(R.id.recipe_ingredient_quantity, getString(R.string.ingredient_quantity_text, ingredient.quantity, ingredient.measure))
                    }
                }
            }

            override fun getLoadingView(): RemoteViews? {
                return null
            }

            override fun getViewTypeCount(): Int {
                return 1
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun hasStableIds(): Boolean {
                return false
            }
        }
    }
}