package com.vycius.tasty.matcher

import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.Toolbar
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class ToolbarViewMatcher {

    fun matchToolbarTitle(
            title: CharSequence): ViewInteraction {
        return Espresso.onView(ViewMatchers.isAssignableFrom(Toolbar::class.java))
                .check(ViewAssertions.matches(withToolbarTitle(Matchers.`is`(title))))
    }

    private fun withToolbarTitle(
            textMatcher: Matcher<CharSequence>): Matcher<Any> {
        return object : BoundedMatcher<Any, Toolbar>(Toolbar::class.java) {
            public override fun matchesSafely(toolbar: Toolbar): Boolean {
                return textMatcher.matches(toolbar.title)
            }

            override fun describeTo(description: Description) {
                description.appendText("with toolbar title: ")
                textMatcher.describeTo(description)
            }
        }
    }
}

