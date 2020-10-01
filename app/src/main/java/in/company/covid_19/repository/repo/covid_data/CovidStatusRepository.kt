package `in`.company.covid_19.repository.repo.covid_data

import android.content.Context
import androidx.lifecycle.LiveData
import `in`.company.covid_19.app.AppExecutors
import `in`.company.covid_19.repository.api.ApiServices
import com.kotlin.mvvm.repository.api.network.NetworkAndDBBoundResource
import `in`.company.covid_19.repository.api.network.Resource
import `in`.company.covid_19.repository.db.covid_data.CovidDataDao
import `in`.company.covid_19.repository.model.covid_data.CovidData
import `in`.company.covid_19.utils.ConnectivityUtil
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CovidStatusRepository @Inject constructor(
    private val covidDataDao: CovidDataDao,
    private val apiServices: ApiServices, private val context: Context,
    private val appExecutors: AppExecutors = AppExecutors()
) {


    fun getCovidDataByCountry(countryName: String): LiveData<Resource<List<CovidData>?>> {

        return object : NetworkAndDBBoundResource<List<CovidData>, List<CovidData>>(appExecutors) {
            override fun saveCallResult(item: List<CovidData>) {
                if (item.isNotEmpty()) {
                   // covidDataDao.deleteAllArticles()
                    covidDataDao.insertArticles(item)
                }
            }

            override fun shouldFetch(data: List<CovidData>?) =
                (ConnectivityUtil.isConnected(context))

            override fun loadFromDb() = covidDataDao.getCovidData(countryName)

            override fun createCall() =
                apiServices.getCovidData(countryName)

        }.asLiveData()
    }

}