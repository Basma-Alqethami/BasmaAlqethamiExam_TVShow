package com.example.basmaalqethamiexamtvshow.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {
    @GET
    fun getData(@Url url: String): Call<Shows>
}