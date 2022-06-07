package com.example.moviesApp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
        val appContext: Context?
            get() = context
    }
}