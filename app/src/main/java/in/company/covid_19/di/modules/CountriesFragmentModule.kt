package `in`.company.covid_19.di.modules

import `in`.company.covid_19.ui.countries.CountriesFragment
import `in`.company.covid_19.ui.countries.LatestTotalFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class CountriesFragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeCountryListFragment(): CountriesFragment

    @ContributesAndroidInjector
    internal abstract fun contributeLatestTotalFragmentFragment(): LatestTotalFragment
}