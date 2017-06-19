package com.vycius.tasty.adapter

import com.vycius.tasty.adapter.delegate.DelegatingAdapter
import com.vycius.tasty.adapter.delegate.RecipeIngredientDelegateAdapter
import com.vycius.tasty.adapter.delegate.RecipeStepsAdapter
import com.vycius.tasty.model.Ingredient
import com.vycius.tasty.model.Step

class RecipeListAdapter(onRecipeStepClicked: RecipeStepsAdapter.OnRecipeStepClicked) : DelegatingAdapter(
        RecipeStepsAdapter(onRecipeStepClicked),
        RecipeIngredientDelegateAdapter()) {

    fun bind(steps: List<Step>, ingredients: List<Ingredient>) {
        items = ingredients + steps
    }

}