package `in`.company.covid_19.repository.repo.countries

import android.content.Context
import androidx.lifecycle.LiveData
import `in`.company.covid_19.app.AppExecutors
import `in`.company.covid_19.repository.api.ApiServices
import `in`.company.covid_19.repository.api.network.Resource
import `in`.company.covid_19.repository.db.countries.CountriesDao
import `in`.company.covid_19.repository.model.countries.Country
import `in`.company.covid_19.utils.ConnectivityUtil
import `in`.company.covid_19.repository.api.network.NetworkAndDBBoundResource
import `in`.company.covid_19.repository.api.network.NetworkBoundResource
import `in`.company.covid_19.repository.model.covid_data.CovidData
import javax.inject.Inject
import javax.inject.Singleton




@Singleton
class CountriesRepository @Inject constructor(
    private val countriesDao: CountriesDao,
    private val apiServices: ApiServices,
    private val context: Context,
    private val appExecutors: AppExecutors
) {


    fun getCountries(): LiveData<Resource<List<Country>?>> {
        return object : NetworkAndDBBoundResource<List<Country>, List<Country>>(appExecutors) {
            override fun saveCallResult(item: List<Country>) {
                if (item.isNotEmpty()) {
                    countriesDao.deleteAllCountries()
                    countriesDao.insertCountries(item)
                }
            }

            override fun shouldFetch(data: List<Country>?) =
                (ConnectivityUtil.isConnected(context))

            override fun loadFromDb() = countriesDao.getCountries()

            override fun createCall() =
                apiServices.getCountrySource()

        }.asLiveData()
    }

    fun getLatestDaily(): LiveData<Resource<List<CovidData>>> {

        return object : NetworkBoundResource<List<CovidData>>() {
            override fun createCall() =
                apiServices.getLatestDaily()

        }.asLiveData()
    }

}