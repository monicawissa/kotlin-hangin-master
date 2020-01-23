package com.m7amdelbana.hanginkotlin.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.m7amdelbana.hanginkotlin.R
import com.m7amdelbana.hanginkotlin.utilities.Internet
import com.m7amdelbana.hanginkotlin.view.auth.LoginActivity
import com.m7amdelbana.hanginkotlin.view.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private var handler: Handler? = null
    private val SPLASH_DELAY: Long = 3000

    private val runnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun checkInternet()=
        if (Internet.isOnline(this)) {
           // Toast.makeText(this, getString(R.string.connected), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show()
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        checkInternet()

        handler = Handler()
        handler!!.postDelayed(runnable, SPLASH_DELAY)
    }
}
