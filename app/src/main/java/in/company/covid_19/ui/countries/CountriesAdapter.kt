package `in`.company.covid_19.ui.countries

import `in`.company.covid_19.R
import `in`.company.covid_19.repository.model.countries.Country
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_country_list.view.*

class CountriesAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {


    var onCountryClicked: ((Country) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.row_country_list, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.bindView(country)
    }


    override fun getItemCount(): Int = countries.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val tv_country_name: TextView = mView.tv_country_name

        init {
            mView.setOnClickListener {
                onCountryClicked?.invoke(countries[adapterPosition])
            }
        }

        fun bindView(country: Country) {
            tv_country_name.text = country.countryName
        }
    }
}
