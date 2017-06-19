package com.vycius.tasty

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vycius.tasty.model.Recipe
import com.vycius.tasty.model.Step
import kotlinx.android.synthetic.main.recipe_steps_pager.*


class RecipeStepsPagerFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private lateinit var recipe: Recipe
    private lateinit var selectedStep: Step

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipe = arguments.getParcelable<Recipe>(ARG_RECIPE) ?: throw IllegalArgumentException("Recipe is required")
        selectedStep = arguments.getParcelable<Step>(ARG_RECIPE_STEP) ?: throw IllegalArgumentException("Recipe step is required")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.recipe_steps_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val stepsSliderPager = StepsSlidePager(recipe, childFragmentManager)
        pager.adapter = stepsSliderPager

        (activity as AppCompatActivity).supportActionBar?.title = selectedStep.shortDescription

        pager.currentItem = recipe.steps.indexOfFirst { s -> selectedStep.id == s.id }
    }

    override fun onStart() {
        super.onStart()

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                (activity as AppCompatActivity).supportActionBar?.title = recipe.steps[position].shortDescription
            }
        })
    }

    override fun onPause() {
        pager.clearOnPageChangeListeners()

        super.onPause()
    }

    private class StepsSlidePager(private val recipe: Recipe, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return RecipeStepFragment.instance(recipe, recipe.steps[position])
        }

        override fun getCount(): Int {
            return recipe.steps.size
        }
    }

    companion object {

        private const val ARG_RECIPE = "recipe"
        private const val ARG_RECIPE_STEP = "recipe_step"

        fun instance(recipe: Recipe, step: Step): RecipeStepsPagerFragment {
            return RecipeStepsPagerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_RECIPE, recipe)
                    putParcelable(ARG_RECIPE_STEP, step)
                }
            }
        }
    }

}
