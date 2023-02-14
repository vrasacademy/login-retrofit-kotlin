package com.vrasacademy.kotlinretrofit.apis


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient{
    private const val baseUrl = "https://academy.vrasmedia.com/";

    fun getInterceptor() : OkHttpClient {
        val logging      = HttpLoggingInterceptor()
        logging.level    = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  okHttpClient
    }

    private fun getRetrofit():Retrofit{
        val retrofit2 = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(getInterceptor())
            .build();
        return  retrofit2;
    }

    fun getApiService():ApiService{
        return getRetrofit().create(ApiService::class.java);
    }
}