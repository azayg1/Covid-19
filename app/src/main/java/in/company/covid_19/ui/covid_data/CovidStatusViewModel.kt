package `in`.company.covid_19.ui.covid_data

import androidx.lifecycle.ViewModel
import `in`.company.covid_19.repository.repo.covid_data.CovidStatusRepository
import javax.inject.Inject


class CovidStatusViewModel @Inject constructor(
    private val covidStatusRepository: CovidStatusRepository
) : ViewModel() {

   fun getCovidDataByCountry(countryKey: String) = covidStatusRepository.getCovidDataByCountry(countryKey)


}