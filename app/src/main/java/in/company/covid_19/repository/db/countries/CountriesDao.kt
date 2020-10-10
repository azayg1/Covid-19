package `in`.company.covid_19.repository.db.countries

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import `in`.company.covid_19.repository.model.countries.Country
import `in`.company.covid_19.repository.model.countries.CountryCovidData

@Dao
interface CountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountries(countries: List<Country>): List<Long>

    @Query("SELECT * FROM countries_table")
    fun getCountries(): LiveData<List<Country>>

    @Query("DELETE FROM countries_table")
    fun deleteAllCountries()



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountryCovidData(list: List<CountryCovidData>): List<Long>

    @Query("SELECT * FROM country_covid_table")
    fun getAllCountryCovidData(): LiveData<List<CountryCovidData>>

    @Query("DELETE FROM country_covid_table")
    fun deleteAllCountriesCovidData()
}