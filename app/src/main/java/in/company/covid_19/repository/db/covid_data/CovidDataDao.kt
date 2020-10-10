package `in`.company.covid_19.repository.db.covid_data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import `in`.company.covid_19.repository.model.covid_data.CovidData


@Dao
interface CovidDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCovidData(list: List<CovidData>): List<Long>

    @Query("SELECT * FROM covid_status_table where country=(:countryName)")
    fun getCovidData(countryName:String): LiveData<List<CovidData>>

   @Query("DELETE FROM covid_status_table")
    fun deleteAll()
}