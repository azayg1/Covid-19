package `in`.company.covid_19.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import `in`.company.covid_19.repository.db.countries.CountriesDao
import `in`.company.covid_19.repository.db.covid_data.CovidDataDao
import `in`.company.covid_19.repository.model.countries.Country
import `in`.company.covid_19.repository.model.covid_data.CovidData


@Database(entities = [CovidData::class, Country::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun covidStatusDao(): CovidDataDao

    abstract fun countriesDao(): CountriesDao
}