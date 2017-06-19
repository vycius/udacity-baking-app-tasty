package com.vycius.tasty.model

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class Step(val id: Int,
                val shortDescription: String,
                val description: String,
                val videoURL: String,
                val thumbnailURL: String) : PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelStep.CREATOR
    }
}