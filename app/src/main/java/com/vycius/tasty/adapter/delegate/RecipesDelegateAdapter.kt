package com.vycius.tasty.adapter.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.vycius.tasty.R
import com.vycius.tasty.extensions.inflate
import com.vycius.tasty.image.GlideApp
import com.vycius.tasty.image.model.VideoThumbnailUrl
import com.vycius.tasty.model.Recipe
import kotlinx.android.synthetic.main.cell_recipe.view.*

class RecipesDelegateAdapter(val onRecipeClicked: (recipe: Recipe) -> Unit)
    : DelegateAdapter<Recipe, RecipesDelegateAdapter.RecipeViewHolder>() {

    override fun isForViewType(item: Any): Boolean {
        return item is Recipe
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, item: Recipe) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecipeViewHolder {
        return RecipeViewHolder(parent)
    }

    inner class RecipeViewHolder(parent: ViewGroup) :
            RecyclerView.ViewHolder(parent.inflate(R.layout.cell_recipe)) {

        fun bind(recipe: Recipe): Unit {
            itemView.run {
                setOnClickListener {
                    onRecipeClicked(recipe)
                }
                recipe_title.text = recipe.name
                recipe_servings.text = recipe.servings.toString()

                if (recipe.image.isNotBlank()) {
                    GlideApp.with(itemView).load(recipe.image).placeholder(R.drawable.recipe_placeholder).into(recipe_image)
                } else {
                    val lastStepWithVideo = recipe.steps.lastOrNull { s -> !s.videoURL.isNullOrBlank() }

                    lastStepWithVideo?.let { s ->
                        GlideApp.with(itemView).load(VideoThumbnailUrl(s.videoURL))
                                .placeholder(R.drawable.recipe_placeholder).into(recipe_image)
                    }
                }
            }
        }
    }
}