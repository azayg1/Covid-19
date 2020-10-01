package `in`.company.covid_19.repository.model.covid_data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



@Entity(tableName = "covid_status_table")
data class CovidData(
    @PrimaryKey
    @SerializedName("country") var country: String,
    @SerializedName("confirmed") var confirmed: Int? = null,
    @SerializedName("recovered") var recovered: Int? = null,
    @SerializedName("critical") var critical: Int? = null,
    @SerializedName("deaths") var deaths: Int? = null,
    @SerializedName("code") var code: String? = null

)
