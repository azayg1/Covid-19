package `in`.company.covid_19.ui

import `in`.company.covid_19.R
import `in`.company.covid_19.ui.countries.CountriesActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class CovidSplashActivity : BaseActivity() {
    private val SCREEN_TIME_OUT: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_splash)

        Looper.getMainLooper()?.let {
            Handler(it).postDelayed({
                Intent(this, CountriesActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }, SCREEN_TIME_OUT)
        }
    }
}