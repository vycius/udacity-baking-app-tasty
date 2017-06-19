package com.vycius.tasty.widget


//class RecipeInfoWidget : AppWidgetProvider() {
//
//    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
//        // There may be multiple widgets active, so update all of them
//        for (appWidgetId in appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId)
//        }
//    }
//
//    override fun onEnabled(context: Context) {
//        // Enter relevant functionality for when the first widget is created
//    }
//
//    override fun onDisabled(context: Context) {
//        // Enter relevant functionality for when the last widget is disabled
//    }
//
//    companion object {
//
//        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
//                                     appWidgetId: Int) {
//
//            val widgetText = context.getString(R.string.appwidget_text)
//            // Construct the RemoteViews object
//            val views = RemoteViews(context.packageName, R.layout.widget_recipe_info)
//            views.setTextViewText(R.id.appwidget_text, widgetText)
//            views.setRemoteAdapter(R.id.widget_list,
//                    Intent(context, StockWidgetRemoteViewsService::class.java))
//
//            val appIntent = Intent(context, MainActivity::class.java)
//            val appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, 0)
//
//            views.setOnClickPendingIntent(R.id.appwidget_text, appPendingIntent)
//
//            // Instruct the widget manager to update the widget
//            appWidgetManager.updateAppWidget(appWidgetId, views)
//        }
//    }
//}