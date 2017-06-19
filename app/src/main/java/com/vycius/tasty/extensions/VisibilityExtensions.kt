package com.vycius.tasty.extensions

import android.view.View

fun View.setGone() {
    visibility = View.GONE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun View.setVisible() {
    visibility = View.VISIBLE
}


/**
 * Makes [View] visible if [visible], otherwise invokes [or] which is [setGone] by default
 */
fun View.setVisibleIf(visible: Boolean, or: View.() -> Unit = { setGone() }) {
    if (visible) {
        setVisible()
    } else {
        or()
    }
}

fun View.setInvisibleIf(visible: Boolean) {
    setVisibleIf(!visible, { setInvisible() })
}

fun View.setGoneIf(visible: Boolean) {
    setVisibleIf(!visible)
}