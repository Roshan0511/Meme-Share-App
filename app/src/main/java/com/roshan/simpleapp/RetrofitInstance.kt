package com.roshan.simpleapp

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private var retofit: Retrofit? = null
        private val BASE_URL: String = "https://meme-api.herokuapp.com/"

        fun getInstance() : Retrofit {
            if (retofit == null){
                val okHttpClient = OkHttpClient()
                val gson: Gson = GsonBuilder().create()

                retofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retofit!!
        }
    }
}