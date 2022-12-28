package com.ozimos.test.data

import com.ozimos.test.util.AccountHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkUtil {

    const val BASE_URL = "http://94.74.86.174:8080/api/"

    @Provides
    @Singleton
    fun getOkhttpClient(accountHelper: AccountHelper): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)

        builder.addInterceptor { chain ->
            val mOriRequest: Request = chain.request()
            val token = accountHelper.tokenAuth
            val request: Request =
                mOriRequest.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .addHeader("Accept", "application/json")
                    .method(mOriRequest.method, mOriRequest.body)
                    .build()
            chain.proceed(request)
        }


        return builder.build()
    }


    @Provides
    fun getRetrofit(client: OkHttpClient): Retrofit {

        val converterFactory = GsonConverterFactory.create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()

        return retrofit
    }
}