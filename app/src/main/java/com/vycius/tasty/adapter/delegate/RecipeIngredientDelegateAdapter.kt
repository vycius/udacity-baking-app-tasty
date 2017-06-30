package com.vycius.tasty.adapter.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.vycius.tasty.R
import com.vycius.tasty.extensions.inflate
import com.vycius.tasty.model.Ingredient
import kotlinx.android.synthetic.main.cell_recipe_ingredient.view.*

class RecipeIngredientDelegateAdapter : DelegateAdapter<Ingredient, RecipeIngredientDelegateAdapter.RecipeIngredientViewHolder>(Ingredient::class.java) {

    override fun onCreateViewHolder(parent: ViewGroup): RecipeIngredientViewHolder {
        return RecipeIngredientViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecipeIngredientViewHolder, item: Ingredient) {
        holder.bind(item)
    }

    inner class RecipeIngredientViewHolder(parent: ViewGroup)
        : RecyclerView.ViewHolder(parent.inflate(R.layout.cell_recipe_ingredient)) {

        fun bind(item: Ingredient) = itemView.run {
            recipe_ingredient_name.text = item.ingredient
            recipe_ingredient_quantity.text = context.getString(R.string.ingredient_quantity_text, item.quantity, item.measure)
        }
    }
}