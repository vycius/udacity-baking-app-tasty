package com.vycius.tasty.model

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class Recipe(val id: Int,
                  val name: String,
                  val ingredients: List<Ingredient>,
                  val steps: List<Step>,
                  val servings: Int,
                  val image: String) : PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelRecipe.CREATOR
    }

}