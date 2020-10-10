package `in`.company.covid_19.repository.model.countries

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "country_covid_table")
data class CountryCovidData(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("name") var countryName: String? = null,
    @SerializedName("alpha2code") var alpha2code: String? = null,
    @SerializedName("confirmed") var confirmed: Int? = null,
    @SerializedName("critical") var critical: Int? = null,
    @SerializedName("recovered") var recovered: Int? = null,
    @SerializedName("deaths") var deaths: Int? = null,
    @SerializedName("code") var code: String? = null
)
