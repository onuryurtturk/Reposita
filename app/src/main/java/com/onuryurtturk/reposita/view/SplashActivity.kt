package com.onuryurtturk.reposita.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplashOperation()
    }


    private fun initSplashOperation() {
        val intent = Intent(this, ReposActivity::class.java)
        startActivity(intent)
        finish()
    }
}
