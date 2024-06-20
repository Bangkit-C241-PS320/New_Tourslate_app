package com.example.new_tourslate.data.retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @FormUrlEncoded
    @POST("/translate")
    fun uploadText(
    @Field("text") text: String
    ): Call<TranslateResponse>


    @GET("/translate/history")
    suspend fun getHistory(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): List<Data>
}