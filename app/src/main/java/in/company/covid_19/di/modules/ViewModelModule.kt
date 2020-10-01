package `in`.company.covid_19.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import `in`.company.covid_19.di.base.ViewModelFactory
import `in`.company.covid_19.ui.countries.CountriesViewModel
import `in`.company.covid_19.ui.covid_data.CovidStatusViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CovidStatusViewModel::class)
    abstract fun bindCovidDataViewModel(covidStatusViewModel: CovidStatusViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(CountriesViewModel::class)
    abstract fun bindCountriesViewModel(countriesViewModel: CountriesViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

