package com.vycius.tasty


import android.content.Intent
import android.support.annotation.IdRes
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.vycius.tasty.extension.getStringFromFile
import com.vycius.tasty.matcher.RecyclerViewMatcher
import com.vycius.tasty.matcher.ToolbarViewMatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    private var server: MockWebServer? = null

    @Before
    fun setUp() {
        server = MockWebServer()
        server!!.start()

        (getInstrumentation().targetContext.applicationContext as TestApp).injectWithApiUrl(server!!.url("/").toString())
    }

    @Test
    fun mainActivityTest() {
        val recipesResponseBody = getInstrumentation().context.getStringFromFile("recipes_200_ok_response.json")

        server!!.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(recipesResponseBody))

        activityTestRule.launchActivity(Intent())

        onView(withRecyclerView(R.id.recycler_view).atPosition(0))
                .check(matches(isDisplayed()))

        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(0, R.id.recipe_title))
                .check(matches(isDisplayed()))
                .check(matches(withText("Nutella Pie")))

        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(0, R.id.recipe_servings))
                .check(matches(isDisplayed()))
                .check(matches(withText("8")))

        onView(withRecyclerView(R.id.recycler_view).atPosition(0))
                .perform(click())

        ToolbarViewMatcher().matchToolbarTitle("Nutella Pie")
    }

    fun withRecyclerView(@IdRes recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    @After
    fun tearDown() {
        server?.shutdown()
    }

}
