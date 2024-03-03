package com.example.savik.services

import com.example.savik.models.Shloka
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ShlokaInerface{

    @GET("slok/{chapter}/{verse}/")
    fun getShloka(
        @Path("chapter") chapter: Int,
        @Path("verse") verse: Int
    ): Call<Shloka>
}

