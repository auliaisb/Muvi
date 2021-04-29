package mobi.mobileforce.nbsassessment.network

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import mobi.mobileforce.nbsassessment.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModule(val applicationContext: Context) {
    fun provideCall(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Content-Type", "application/json;charset=utf-8")
                    .addHeader("Authorization", "Bearer ${BuildConfig.apiToken}")
                    .build()
                val response = chain.proceed(request)
                response
            }.connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(ChuckInterceptor(applicationContext))
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASEURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
    }

}