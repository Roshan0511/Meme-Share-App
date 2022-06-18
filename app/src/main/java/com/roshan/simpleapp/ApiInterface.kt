package com.roshan.simpleapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("gimme")
    fun getMeme(): Call<Pojo>
}