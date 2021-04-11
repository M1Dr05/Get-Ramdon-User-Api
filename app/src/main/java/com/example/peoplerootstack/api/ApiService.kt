package com.example.peoplerootstack.api

import com.example.peoplerootstack.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object ApiService {

    private var url = BuildConfig.SERVER_URL

    private var http = OkHttpClient.Builder()
    var apiService: ApiPerson? = null
    private var retrofit : Retrofit?= null

    init {
        apiService = create()
    }

    private fun builder() : Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addConverterFactory(GsonConverterFactory.create())
    }

    private fun create(): ApiPerson {

        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)
        http.addInterceptor(logging)

        http.callTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
        retrofit = builder().client(http.build()).build()
        return  retrofit!!.create(ApiPerson::class.java)

    }

}