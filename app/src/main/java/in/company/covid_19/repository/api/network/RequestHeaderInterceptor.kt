package `in`.company.covid_19.repository.api.network

import `in`.company.covid_19.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val build =
            chain.request().newBuilder()
                .addHeader("x-rapidapi-host",BuildConfig.HOST )
                .addHeader("x-rapidapi-key", BuildConfig.API_KEY)
                .build()
        return chain.proceed(build)
    }
}