package com.vycius.tasty.adapter

import com.vycius.tasty.adapter.delegate.RecipesDelegateAdapter
import com.vycius.tasty.adapter.delegate.DelegatingAdapter
import com.vycius.tasty.model.Recipe

class RecipesAdapter(onRecipeClicked: (recipe: Recipe) -> Unit) : DelegatingAdapter(
        RecipesDelegateAdapter(onRecipeClicked)) {

    fun bind(recipes: List<Recipe>) {
        items = recipes
    }


}