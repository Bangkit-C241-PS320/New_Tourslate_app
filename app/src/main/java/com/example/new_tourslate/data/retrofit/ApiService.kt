package com.example.new_tourslate.data.retrofit

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiService {
    @POST("")
    fun uploadText(
    @Part("translate") translate: RequestBody
    ): Call<>

    @GET("")
    fun getText() : Call<>
}