package `in`.company.covid_19.ui.countries

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import `in`.company.covid_19.R
import `in`.company.covid_19.repository.model.countries.Country
import `in`.company.covid_19.ui.covid_data.CovidStatusActivity
import android.widget.Toast

import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_country_list.*
import javax.inject.Inject

class CountriesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val countriesViewModel: CountriesViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var countriesAdapter: CountriesAdapter
    private var listOfCountries = ArrayList<Country>()



    companion object {

        @JvmStatic
        fun newInstance() =
            CountriesFragment().apply {
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
        fetchCountry()
    }

    private fun bindView() {
        recyclerview_countries.layoutManager = LinearLayoutManager(context)
        countriesAdapter = CountriesAdapter(listOfCountries)
        recyclerview_countries.adapter = countriesAdapter
        countriesAdapter.onCountryClicked = { country ->
            val intent = Intent(context, CovidStatusActivity::class.java)
            intent.putExtra(CovidStatusActivity.KEY_COUNTRY_NAME, country.countryName)
            startActivity(intent)
        }
    }

    private fun fetchCountry() {
        countriesViewModel.getCountries().observe(viewLifecycleOwner, Observer {

            when {
                it.status.isLoading() -> {
                    //TODO show loading or shimmer
                }
                it.status.isSuccessful() -> {
                    listOfCountries.clear()
                    it.data?.let { it1 -> listOfCountries.addAll(it1) }
                    countriesAdapter.notifyDataSetChanged()
                }
                it.status.isError() -> {
                    if (it.errorMessage != null)
                        context?.let { context ->
                            Toast.makeText(context, it.errorMessage.toString(),Toast.LENGTH_SHORT).show()
                        }

                }
            }
        })
    }
}
