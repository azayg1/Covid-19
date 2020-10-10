package `in`.company.covid_19.repository.api

import `in`.company.covid_19.repository.api.network.Resource
import `in`.company.covid_19.repository.model.countries.Country
import `in`.company.covid_19.repository.model.countries.CountryCovidData
import `in`.company.covid_19.repository.model.covid_data.CovidData
import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServices {

    @GET("/country")
    fun getCovidDataByCountryName(@Query("name") countryName: String): LiveData<Resource<List<CovidData>>>


    @GET("/help/countries")
    fun getCountrySource(): LiveData<Resource<List<Country>>>

    @GET("/help/countries")
    fun getAllCountryCovidData(): LiveData<Resource<List<CountryCovidData>>>

    @GET("/totals")
    fun getLatestDaily(): LiveData<Resource<List<CovidData>>>


}
