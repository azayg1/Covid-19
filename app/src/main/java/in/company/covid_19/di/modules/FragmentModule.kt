package `in`.company.covid_19.di.modules

import `in`.company.covid_19.ui.countries.CountriesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeCountryListFragment(): CountriesFragment
}