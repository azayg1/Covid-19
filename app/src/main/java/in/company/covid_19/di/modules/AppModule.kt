package `in`.company.covid_19.di.modules

import `in`.company.covid_19.BuildConfig
import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import `in`.company.covid_19.app.App
import `in`.company.covid_19.repository.api.ApiServices
import `in`.company.covid_19.repository.api.network.LiveDataCallAdapterFactoryForRetrofit
import `in`.company.covid_19.repository.api.network.RequestHeaderInterceptor
import `in`.company.covid_19.repository.db.AppDatabase
import `in`.company.covid_19.repository.db.countries.CountriesDao
import `in`.company.covid_19.repository.db.covid_data.CovidDataDao
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [PreferencesModule::class, ActivityModule::class, ViewModelModule::class])
class AppModule {


    companion object {
        private const val BASE_URL = BuildConfig.BASE_URL
    }


    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(RequestHeaderInterceptor())
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideCovidService(client: OkHttpClient): ApiServices {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactoryForRetrofit())
            .build()
            .create(ApiServices::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "covid-19-db")
            .fallbackToDestructiveMigration().build()



    @Singleton
    @Provides
    fun provideCovidDataDao(db: AppDatabase): CovidDataDao = db.covidStatusDao()

    @Singleton
    @Provides
    fun provideCountriesDao(db: AppDatabase): CountriesDao = db.countriesDao()



    @Singleton
    @Provides
    fun provideContext(application: App): Context = application.applicationContext


    @Provides
    @Singleton
    fun providesResources(application: App): Resources = application.resources
}
