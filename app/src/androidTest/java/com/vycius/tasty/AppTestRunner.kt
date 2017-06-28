package com.vycius.tasty

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner


class AppTestRunner : AndroidJUnitRunner() {

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        val testApplicationClassName = TestApp::class.java.canonicalName
        return super.newApplication(cl, testApplicationClassName, context)
    }
}