package com.vycius.tasty.adapter.delegate

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlin.properties.Delegates

open class DelegatingAdapter(vararg private val adapters: DelegateAdapter<out Any, out RecyclerView.ViewHolder>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var items by Delegates.observable(listOf<Any>()) {
        _, oldItems, newItems ->
        notifyItemsChanged(oldItems, newItems)
    }

    override final fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return adapters[viewType].onCreateViewHolder(parent)
    }

    override final fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        adapters[getItemViewType(position)].onBindViewHolderItem(holder, items[position])
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        val index = adapters.indexOfFirst { adapter -> adapter.isForViewType(item) }

        if (index == -1) {
            throw IllegalArgumentException("Unable to find $item while binding view holder")
        }

        return index
    }

    override final fun getItemCount(): Int {
        return items.size
    }

    private fun notifyItemsChanged(oldItems: List<Any>, newItems: List<Any>) {
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    oldItems[oldItemPosition]::class.java == newItems[newItemPosition]::class.java

            override fun getOldListSize(): Int = oldItems.size

            override fun getNewListSize(): Int = newItems.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    oldItems[oldItemPosition] == newItems[newItemPosition]

        }).dispatchUpdatesTo(this)
    }
}