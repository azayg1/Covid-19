package `in`.company.covid_19.api

import `in`.company.covid_19.repository.api.ApiServices
import `in`.company.covid_19.repository.api.network.LiveDataCallAdapterFactoryForRetrofit
import `in`.company.covid_19.repository.api.network.Resource
import androidx.annotation.Nullable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@Suppress("UNCHECKED_CAST")
@RunWith(JUnit4::class)
class ApiServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: ApiServices

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactoryForRetrofit())
            .build()
            .create(ApiServices::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestCountryList() {
        enqueueResponse("country_list.json")
        val response = (getOrAwaitValue(service.getCountrySource()) as Resource)
        MatcherAssert.assertThat(response.data?.size, `is`(253))
        MatcherAssert.assertThat(response.data?.get(0)?.countryName, `is`("Afghanistan"))
        MatcherAssert.assertThat(response.data?.get(1)?.countryName, `is`("Åland Islands"))
        MatcherAssert.assertThat(response.data?.get(10)?.countryName, `is`("Argentina"))

    }

    @Test
    fun requestTotals() {
        enqueueResponse("daily_total.json")
        val response = (getOrAwaitValue(service.getLatestDaily()) as Resource)
        MatcherAssert.assertThat(response.data?.size, `is`(1))
        MatcherAssert.assertThat(response.data?.get(0)?.confirmed, `is`(37345501))
        MatcherAssert.assertThat(response.data?.get(0)?.critical, `is`(68602))
        MatcherAssert.assertThat(response.data?.get(0)?.deaths, `is`(1080658))

    }

    @Test
    fun requestCovidDataByCountryName() {
        enqueueResponse("data_by_country.json")
        val response = (getOrAwaitValue(service.getCovidDataByCountryName("Afghanistan")) as Resource)
        MatcherAssert.assertThat(response.data?.size, `is`(1))
        MatcherAssert.assertThat(response.data?.get(0)?.country, `is`("Afghanistan"))
        MatcherAssert.assertThat(response.data?.get(0)?.confirmed, `is`(39703))
        MatcherAssert.assertThat(response.data?.get(0)?.critical, `is`(93))
        MatcherAssert.assertThat(response.data?.get(0)?.deaths, `is`(1473))

    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }

    @Throws(InterruptedException::class)
    fun <T> getOrAwaitValue(liveData: LiveData<T>): T? {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer: Observer<T> = object : Observer<T> {
            override fun onChanged(@Nullable o: T) {
                data[0] = o
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)
        return data[0] as T?
    }
}
