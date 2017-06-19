package com.vycius.tasty.model

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class Ingredient(val quantity: String, val measure: String, val ingredient: String)
    : PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelIngredient.CREATOR
    }
}