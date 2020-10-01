package `in`.company.covid_19.ui.countries


import `in`.company.covid_19.R
import `in`.company.covid_19.ui.BaseActivity
import android.os.Bundle


class CountriesActivity() : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countires)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, CountriesFragment.newInstance())
                .commit()
        }
    }
}
