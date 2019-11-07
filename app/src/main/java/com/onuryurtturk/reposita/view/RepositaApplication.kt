package com.onuryurtturk.reposita.view

import android.app.Application
import android.os.SystemClock

class RepositaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SystemClock.sleep(2000)
    }

}