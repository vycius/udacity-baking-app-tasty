package com.vycius.tasty


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.vycius.tasty.adapter.RecipeListAdapter
import com.vycius.tasty.adapter.delegate.RecipeStepsAdapter
import com.vycius.tasty.manager.RecipeInfoWidgetManager
import com.vycius.tasty.model.Recipe
import com.vycius.tasty.model.Step
import kotlinx.android.synthetic.main.recipe_detail_list.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


/**
 * An activity representing a list of Recipes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [RecipeStepActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class RecipeDetailActivity : AppCompatActivity(), RecipeStepsAdapter.OnRecipeStepClicked {

    @Inject
    lateinit var recipeInfoWidgetManager: RecipeInfoWidgetManager

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane: Boolean = false

    private lateinit var recipe: Recipe
    private lateinit var recipesAdapter: RecipeListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        App.get(this).component.inject(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.title = title

        recipe = intent.extras.getParcelable(ARG_RECIPE) ?: throw IllegalArgumentException("Pass recipe")

        setupRecyclerView(recycler_view)

        if (findViewById(R.id.recipe_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.recipe_info, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_add_to_widget -> {
                recipeInfoWidgetManager.updateWidgetRecipe(recipe)
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    override fun onStart() {
        super.onStart()

        title = recipe.name

        recipesAdapter.bind(recipe.steps, recipe.ingredients)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recipesAdapter = RecipeListAdapter(this)

        recyclerView.adapter = recipesAdapter
    }

    override fun onRecipeStepClicked(step: Step) {
        if (mTwoPane) {
            val fragment = RecipeStepsPagerFragment.instance(recipe, step)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.recipe_detail_container, fragment)
                    .commit()
        } else {
            val intent = Intent(this, RecipeStepActivity::class.java)
            intent.putExtra(ARG_RECIPE, recipe)
            intent.putExtra(ARG_RECIPE_STEP, step)

            startActivity(intent)
        }
    }

    companion object {
        private const val ARG_RECIPE = "recipe"
        private const val ARG_RECIPE_STEP = "recipe_step"
    }
}
