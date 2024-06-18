package com.example.new_tourslate.data.retrofit

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiService {
    @FormUrlEncoded
    @POST("/translate")
    fun uploadText(
    @Field("text") text: String
    ): Call<TranslateResponse>
}