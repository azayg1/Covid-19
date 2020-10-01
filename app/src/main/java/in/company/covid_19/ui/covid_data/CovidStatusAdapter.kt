package `in`.company.covid_19.ui.covid_data

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.company.covid_19.R
import `in`.company.covid_19.repository.model.covid_data.CovidData
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.row_covid_data.view.*


class CovidStatusAdapter(private var covidDataList: ArrayList<CovidData>) : RecyclerView.Adapter<CovidStatusAdapter.CovidDataHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CovidDataHolder( LayoutInflater.from(parent.context).inflate(R.layout.row_covid_data,parent,false))


    override fun onBindViewHolder(covidDataHolder: CovidDataHolder, position: Int) =
        covidDataHolder.bind(covidDataList[position])


    override fun getItemCount() = covidDataList.size


    inner class CovidDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(covidData: CovidData) = with(itemView) {
            confirmed.text = itemView.context.getString(R.string.text_confirmed,covidData.confirmed)
            recovered.text = itemView.context.getString(R.string.text_recovered,covidData.recovered)
            critical.text = itemView.context.getString(R.string.text_critical,covidData.critical)
            death.text = itemView.context.getString(R.string.text_death,covidData.deaths)
        }
    }

}