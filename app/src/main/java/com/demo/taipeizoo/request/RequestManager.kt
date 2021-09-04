package com.demo.taipeizoo.request

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestManager {

    private const val DOMAIN = "https://data.taipei/"

    private val api: Api by lazy {
        initApi()
    }

    private val convertFactory: GsonConverterFactory by lazy {
        GsonConverterFactory.create()
    }

    @Synchronized
    fun colorApi(): Api {
        return api
    }

    private fun initApi(): Api {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(convertFactory)
            .baseUrl(DOMAIN)
            .build()
        return retrofit.create(Api::class.java)
    }
}