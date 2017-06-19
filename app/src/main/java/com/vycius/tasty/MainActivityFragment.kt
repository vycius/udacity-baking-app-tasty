package com.vycius.tasty

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vycius.tasty.adapter.RecipesAdapter
import com.vycius.tasty.extensions.setGone
import com.vycius.tasty.extensions.setVisible
import com.vycius.tasty.model.Recipe
import com.vycius.tasty.service.RecipesService
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    @Inject
    lateinit var recipesService: RecipesService

    private var recipes: List<Recipe> = listOf()

    private lateinit var recipesAdapter: RecipesAdapter

    var recipesCall: Call<List<Recipe>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipes = savedInstanceState?.getParcelableArrayList<Recipe>(ARG_RECIPES_LIST) ?: listOf<Recipe>()

        App.get(context).component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val gridColumnNumber = resources.getInteger(R.integer.grid_column_number)

        recipesAdapter = RecipesAdapter(this::onRecipeClicked)
        recycler_view.layoutManager = GridLayoutManager(context, gridColumnNumber)
        recycler_view.adapter = recipesAdapter

        if (recipes.isNotEmpty()) {
            setRecipes(recipes)
        }
    }

    override fun onStart() {
        super.onStart()

        recipesCall = recipesService.recipes()
        recipesCall!!.enqueue(object : Callback<List<Recipe>> {
            override fun onFailure(call: Call<List<Recipe>>, throwable: Throwable) {
                if (!call.isCanceled) {
                    Toast.makeText(context, R.string.recipes_retrieve_error,
                            Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                setRecipes(response.body()!!)
            }
        })
    }

    override fun onStop() {
        recipesCall?.cancel()
        super.onStop()
    }

    private fun setRecipes(recipes: List<Recipe>) {
        this.recipes = recipes
        recipesAdapter.bind(recipes)

        recycler_view.setVisible()
        progress_bar.setGone()
    }

    private fun onRecipeClicked(recipe: Recipe) {
        val intent = Intent(context, RecipeDetailActivity::class.java)
        intent.putExtra(ARG_RECIPE, recipe)

        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(ARG_RECIPES_LIST, ArrayList(recipes))

        super.onSaveInstanceState(outState)
    }


    companion object {
        private const val ARG_RECIPE = "recipe"
        private const val ARG_RECIPES_LIST = "recipes_list"
    }
}
