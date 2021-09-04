package com.demo.taipeizoo.request

import com.demo.taipeizoo.model.PlantDataList
import com.demo.taipeizoo.model.ZooAreaDataList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    fun getZooAreaDataList(
        @Query("scope") scope: String = "resourceAquire",
        @Query("limit") limit: Int = 1000,
        @Query("offset") offset: Int = 0
    ): Call<Result<ZooAreaDataList>>

    @GET("api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14")
    fun getPlantDataList(
        @Query("scope") scope: String = "resourceAquire",
        @Query("limit") limit: Int = 1000,
        @Query("offset") offset: Int = 0
    ): Call<Result<PlantDataList>>

}