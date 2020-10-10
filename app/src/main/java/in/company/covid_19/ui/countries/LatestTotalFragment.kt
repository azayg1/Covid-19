package `in`.company.covid_19.ui.countries

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import `in`.company.covid_19.R
import `in`.company.covid_19.repository.model.covid_data.CovidData
import android.widget.Toast

import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_country_list.*
import kotlinx.android.synthetic.main.layout_progress.*
import javax.inject.Inject

class LatestTotalFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val countriesViewModel: CountriesViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var countriesAdapter: DailyDataAdapter
    private var dailyDataList = ArrayList<CovidData>()



    companion object {

        @JvmStatic
        fun newInstance() =
            LatestTotalFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this) // Providing the dependency
        super.onAttach(context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_country_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
        fetchDailyLatestData()
    }

    private fun bindView() {
        recyclerview_countries.layoutManager = LinearLayoutManager(context)
        countriesAdapter = DailyDataAdapter(dailyDataList)
        recyclerview_countries.adapter = countriesAdapter
        countriesAdapter.onDailyDataClicked = { country ->

        }
    }

    private fun fetchDailyLatestData() {
        countriesViewModel.getLatestTotal().observe(viewLifecycleOwner, Observer {

            when {
                it.status.isLoading() -> {
                    progress_view.visibility=View.VISIBLE
                }
                it.status.isSuccessful() -> {
                    progress_view.visibility=View.GONE
                    dailyDataList.clear()
                    it.data?.let { it1 -> dailyDataList.addAll(it1) }
                    countriesAdapter.notifyDataSetChanged()
                }
                it.status.isError() -> {
                    progress_view.visibility=View.GONE
                    if (it.errorMessage != null)
                        context?.let { context ->
                            Toast.makeText(context, it.errorMessage.toString(),Toast.LENGTH_SHORT).show()
                        }

                }
            }
        })
    }
}
