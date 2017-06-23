package com.vycius.tasty.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.vycius.tasty.App
import com.vycius.tasty.R
import com.vycius.tasty.manager.RecipeInfoWidgetManager
import com.vycius.tasty.service.RecipeInfoWidgetRemoteViewService
import javax.inject.Inject


class RecipeInfoWidget : AppWidgetProvider() {

    @Inject
    lateinit var recipeInfoWidgetManager: RecipeInfoWidgetManager

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        App.get(context.applicationContext).component.inject(this)

        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, recipeInfoWidgetManager)
        }
    }

    override fun onEnabled(context: Context) {
    }

    override fun onDisabled(context: Context) {
    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int, recipeInfoWidgetManager: RecipeInfoWidgetManager) {
            val widgetText = recipeInfoWidgetManager.getRecipe()?.name ?: context.getString(R.string.widget_no_recipe)

            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.widget_recipe_info)
            views.setTextViewText(R.id.widget_text, widgetText)
            views.setRemoteAdapter(R.id.widget_list,
                    Intent(context, RecipeInfoWidgetRemoteViewService::class.java))

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}