package com.jubjub.user.model.network

import com.jubjub.user.di.app.MyApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetRetrofit {

    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val  client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val  retrofit = Retrofit.Builder()
            .baseUrl(MyApplication.BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getAuthApi() : AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    fun getEquipmentApi(): EquipmentApi {
        return retrofit.create(EquipmentApi::class.java)
    }


}

