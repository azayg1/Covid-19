package `in`.company.covid_19.ui.countries


import `in`.company.covid_19.R
import `in`.company.covid_19.ui.BaseActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_countires.*


class CountriesActivity() : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countires)


        navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, CountriesFragment.newInstance())
                        .commit()
                    true
                }
                R.id.page_2 -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, LatestTotalFragment.newInstance())
                        .commit()
                   true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
           navigation.selectedItemId=R.id.page_1
        }
    }
}
