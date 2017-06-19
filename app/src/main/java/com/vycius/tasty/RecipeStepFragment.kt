package com.vycius.tasty

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.vycius.tasty.extensions.setGoneIf
import com.vycius.tasty.model.Recipe
import com.vycius.tasty.model.Step
import kotlinx.android.synthetic.main.recipe_step.*


/**
 * A fragment representing a single Recipe detail screen.
 * This fragment is either contained in a [RecipeListActivity]
 * in two-pane mode (on tablets) or a [RecipeDetailActivity]
 * on handsets.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class RecipeStepFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private lateinit var recipe: Recipe
    private lateinit var recipeStep: Step

    private var exoPlayer: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipe = arguments.getParcelable<Recipe>(ARG_RECIPE) ?: throw IllegalArgumentException("Recipe is required")
        recipeStep = arguments.getParcelable<Step>(ARG_RECIPE_STEP) ?: throw IllegalArgumentException("Recipe step is required")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.recipe_step, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipe_step_description.text = recipeStep.description
        recipe_step_video.setGoneIf(recipeStep.videoURL.isNullOrEmpty())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (recipeStep.videoURL.isNotEmpty()) {
            setVideo()
        }
    }

    private fun setVideo() {
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
        recipe_step_video.player = exoPlayer

        val mp4VideoUri = Uri.parse(recipeStep.videoURL)

        val bandwidthMeterA = DefaultBandwidthMeter()

        val dataSourceFactory = DefaultDataSourceFactory(context, "BakingApp", bandwidthMeterA)
        val extractorsFactory = DefaultExtractorsFactory()

        val videoSource = ExtractorMediaSource(mp4VideoUri,
                dataSourceFactory, extractorsFactory, null, null)
        exoPlayer?.prepare(videoSource)
    }

    override fun onDestroy() {
        exoPlayer?.release()
        super.onDestroy()
    }

    companion object {
        private const val ARG_RECIPE = "recipe"
        private const val ARG_RECIPE_STEP = "recipe_step"

        fun instance(recipe: Recipe, step: Step): RecipeStepFragment {
            return RecipeStepFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_RECIPE, recipe)
                    putParcelable(ARG_RECIPE_STEP, step)
                }
            }
        }
    }
}
