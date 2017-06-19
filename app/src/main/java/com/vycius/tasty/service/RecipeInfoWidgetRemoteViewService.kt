package com.vycius.tasty.service


//class RecipeInfoWidgetRemoteViewService : RemoteViewsService() {
//    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
//        return object : RemoteViewsFactory {
//
//
//            override fun onCreate() {}
//
//            override fun onDataSetChanged() {
//                if (data != null) {
//                    data!!.close()
//                }
//
//                val identityToken = Binder.clearCallingIdentity()
//
//
//                Binder.restoreCallingIdentity(identityToken)
//            }
//
//            override fun onDestroy() {
//                if (data != null) {
//                    data!!.close()
//                    data = null
//                }
//            }
//
//            override fun getCount(): Int {
//                return if (data == null) 0 else data!!.getCount()
//            }
//
//            override fun getViewAt(position: Int): RemoteViews? {
//                if (position == AdapterView.INVALID_POSITION ||
//                        data == null || !data!!.moveToPosition(position)) {
//                    return null
//                }
//
//                val views = RemoteViews(packageName, R.layout.list_item_quote)
//                val symbol = data!!.getString(data!!.getColumnIndex("symbol"))
//
//                views.setTextViewText(R.id.stock_symbol, symbol)
//                views.setTextViewText(R.id.bid_price, data!!.getString(data!!.getColumnIndex("bid_price")))
//                views.setTextViewText(R.id.change, data!!.getString(data!!.getColumnIndex("percent_change")))
//
//
//                if (data!!.getInt(data!!.getColumnIndex("is_up")) === 1) {
//                    views.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_green)
//                } else {
//                    views.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_red)
//                }
//
//                return views
//            }
//
//            override fun getLoadingView(): RemoteViews? {
//                return null
//            }
//
//            override fun getViewTypeCount(): Int {
//                return 1
//            }
//
//            override fun getItemId(position: Int): Long {
//                if (data!!.moveToPosition(position))
//                    return data!!.getLong(data!!.getColumnIndexOrThrow(QuoteColumns._ID))
//                return position.toLong()
//            }
//
//            override fun hasStableIds(): Boolean {
//                return true
//            }
//        }
//    }
//}