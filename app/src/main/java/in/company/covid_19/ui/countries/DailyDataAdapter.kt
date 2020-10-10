package `in`.company.covid_19.ui.countries

import `in`.company.covid_19.R
import `in`.company.covid_19.repository.model.covid_data.CovidData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_covid_daily_data.view.*

class DailyDataAdapter(private val list: List<CovidData>) :
    RecyclerView.Adapter<DailyDataAdapter.ViewHolder>() {


    var onDailyDataClicked: ((CovidData) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.row_covid_daily_data, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.bindView(data)
    }


    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        init {
            mView.setOnClickListener {
                onDailyDataClicked?.invoke(list[adapterPosition])
            }
        }

        fun bindView(covidData: CovidData) = with(mView) {
            confirmed.text =
                itemView.context.getString(R.string.text_confirmed, covidData.confirmed)
            recovered.text =
                itemView.context.getString(R.string.text_recovered, covidData.recovered)
            critical.text = itemView.context.getString(R.string.text_critical, covidData.critical)
            death.text = itemView.context.getString(R.string.text_death, covidData.deaths)
        }
    }
}
