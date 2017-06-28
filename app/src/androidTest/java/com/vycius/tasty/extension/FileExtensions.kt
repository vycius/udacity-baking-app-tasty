package com.vycius.tasty.extension

import android.content.Context


fun Context.getStringFromFile(filePath: String): String {
    return resources.assets.open(filePath)?.use {
        it.bufferedReader().use { it.readText() }
    } ?: throw NullPointerException("Unable to read text from $filePath")
}
