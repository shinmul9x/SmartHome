package com.example.smarthome.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private var retrofit: Retrofit? = null
    fun getClient(baseUrl: String): Retrofit? {
        if (retrofit == null) {
            retrofit = buildRetrofit(baseUrl)
        } else if (retrofit?.baseUrl()?.equals(baseUrl)!!) {
            retrofit = buildRetrofit(baseUrl)
        }
        return retrofit
    }

    private fun buildRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}