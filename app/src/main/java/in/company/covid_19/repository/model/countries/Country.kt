package `in`.company.covid_19.repository.model.countries

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "countries_table")
data class Country(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("name") var countryName: String? = null,
    @SerializedName("alpha2code") var alpha2code: String? = null
)