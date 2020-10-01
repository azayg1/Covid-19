package `in`.company.covid_19.ui.covid_data

import `in`.company.covid_19.R
import `in`.company.covid_19.repository.model.covid_data.CovidData
import `in`.company.covid_19.ui.BaseActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_covid_data_list.*
import kotlinx.android.synthetic.main.layout_progress.*


class CovidStatusActivity : BaseActivity() {

    companion object {
        const val KEY_COUNTRY_NAME: String = "key_country_name"
    }

    private lateinit var adapter: CovidStatusAdapter

    private val covidStatusArticleViewModel: CovidStatusViewModel by viewModels {
        viewModelFactory
    }
    private var covidDataList = ArrayList<CovidData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_data_list)
        adapter = CovidStatusAdapter(covidDataList)
        covid_data_list.layoutManager = LinearLayoutManager(this)
        covid_data_list.adapter = adapter
        intent?.getStringExtra(KEY_COUNTRY_NAME)?.let { countryName ->
            loadCovidDataByCountry(countryName)
        }
    }

    private fun loadCovidDataByCountry(countryKey: String) {
        covidStatusArticleViewModel.getCovidDataByCountry(countryKey).observe(this, Observer {
            when {
                it.status.isLoading() -> {
                    progress_view.visibility = View.VISIBLE
                }
                it.status.isSuccessful() -> {
                    progress_view.visibility = View.GONE
                    covidDataList.clear()
                    it.data?.let { it1 -> covidDataList.addAll(it1) }
                    adapter.notifyDataSetChanged()

                }
                it.status.isError() -> {
                    progress_view.visibility = View.GONE
                    if (it.errorMessage != null)
                        Toast.makeText(this, it.errorMessage.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
