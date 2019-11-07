package com.onuryurtturk.reposita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onuryurtturk.reposita.view.ReposActivity

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
