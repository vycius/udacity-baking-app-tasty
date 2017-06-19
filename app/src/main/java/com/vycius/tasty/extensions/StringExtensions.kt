package com.vycius.tasty.extensions

fun String.emptyToNull(): String? {
    return if (this.isNullOrEmpty()) null else this
}