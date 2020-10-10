package `in`.company.covid_19.ui.countries

import `in`.company.covid_19.repository.repo.countries.CountriesRepository
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CountriesViewModel @Inject constructor(var countriesRepository: CountriesRepository) :
    ViewModel() {

    fun getCountries() = countriesRepository.getCountries()

    fun getLatestTotal() = countriesRepository.getLatestDaily()

    fun getAllCountryCovidData() = countriesRepository.getAllCountryCovidData()
}