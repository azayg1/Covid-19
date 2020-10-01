package `in`.company.covid_19.repository.db.countries

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import `in`.company.covid_19.repository.model.countries.Country

@Dao
interface CountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountries(countries: List<Country>): List<Long>

    @Query("SELECT * FROM countries_table")
    fun getCountries(): LiveData<List<Country>>


    @Query("DELETE FROM countries_table")
    abstract fun deleteAllCountries()
}