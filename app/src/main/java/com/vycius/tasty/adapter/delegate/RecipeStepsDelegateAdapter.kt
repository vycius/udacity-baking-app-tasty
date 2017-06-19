package com.vycius.tasty.adapter.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.vycius.tasty.R
import com.vycius.tasty.extensions.emptyToNull
import com.vycius.tasty.extensions.inflate
import com.vycius.tasty.image.GlideApp
import com.vycius.tasty.image.model.VideoThumbnailUrl
import com.vycius.tasty.model.Step
import kotlinx.android.synthetic.main.cell_recipe_step.view.*

class RecipeStepsAdapter(private val onRecipeStepClicked: OnRecipeStepClicked)
    : DelegateAdapter<Step, RecipeStepsAdapter.RecipeStepsViewHolder>() {

    override fun isForViewType(item: Any): Boolean {
        return item is Step
    }

    override fun onBindViewHolder(holder: RecipeStepsViewHolder, item: Step) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecipeStepsViewHolder {
        return RecipeStepsViewHolder(parent)
    }

    inner class RecipeStepsViewHolder(parent: ViewGroup)
        : RecyclerView.ViewHolder(parent.inflate(R.layout.cell_recipe_step)) {
        fun bind(item: Step) = itemView.run {
            setOnClickListener {
                onRecipeStepClicked.onRecipeStepClicked(item)
            }
            recipe_step_title.text = item.shortDescription

            if (item.thumbnailURL.isNotBlank()) {
                GlideApp.with(itemView).load(item.thumbnailURL).placeholder(R.drawable.recipe_placeholder).into(recipe_step_image)
            } else {
                item.videoURL.emptyToNull()?.let {
                    url ->
                    GlideApp.with(itemView).load(VideoThumbnailUrl(url))
                            .placeholder(R.drawable.recipe_placeholder).into(recipe_step_image)
                }
            }
        }
    }

    interface OnRecipeStepClicked {
        fun onRecipeStepClicked(step: Step)
    }
}


