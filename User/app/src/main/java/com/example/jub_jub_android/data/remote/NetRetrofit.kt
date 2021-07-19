package com.example.jub_jub_android.data.remote

import com.example.jub_jub_android.di.app.MyApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetRetrofit {



    val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    var retrofit = Retrofit.Builder()
        .baseUrl(MyApplication.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getServiceApi() : Api {
        return retrofit.create(Api::class.java)
    }


}

