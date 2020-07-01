package com.example.architecturestudy.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MovieApiService {

    private val naverApiUrl = "https://openapi.naver.com/v1/search/"

    var movieApiService: MovieApi = setRetrofit().create(MovieApi::class.java)

    private val readWriteTimeOutSec: Long = 20

    fun createService(): MovieApi {
        return movieApiService
    }

    private fun setRetrofit(): Retrofit {

        val mHttpLoggingIntercepter = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(readWriteTimeOutSec, TimeUnit.SECONDS)
            .writeTimeout(readWriteTimeOutSec, TimeUnit.SECONDS)
            .addInterceptor(mHttpLoggingIntercepter)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .addHeader("X-Naver-Client-Id", "JSnG5WlnTivnbGj7NFqL")
                    .addHeader("X-Naver-Client-Secret", "iDSdCN3VjL").build()
                chain.proceed(request)
            }

        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(naverApiUrl)
            .client(mOkHttpClient.build())
            .build()
    }
}