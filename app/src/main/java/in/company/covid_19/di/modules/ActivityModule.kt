package `in`.company.covid_19.di.modules

import `in`.company.covid_19.ui.CovidSplashActivity
import `in`.company.covid_19.ui.countries.CountriesActivity
import `in`.company.covid_19.ui.covid_data.CovidStatusActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeCovidStatusActivity(): CovidStatusActivity

    @ContributesAndroidInjector
    abstract fun contributeCovidSplashActivity(): CovidSplashActivity

    @ContributesAndroidInjector(modules = [CountriesFragmentModule::class])
    abstract fun contributeCountryListingActivity(): CountriesActivity
}
