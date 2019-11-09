package com.onuryurtturk.reposita

import android.app.Application
import android.content.Context
import android.os.SystemClock

class RepositaApplication : Application() {

    init {
        instance = this
    }
    companion object {
        private var instance: RepositaApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
    override fun onCreate() {
        super.onCreate()
        val context: Context = RepositaApplication.applicationContext()
        SystemClock.sleep(2000)
    }

}