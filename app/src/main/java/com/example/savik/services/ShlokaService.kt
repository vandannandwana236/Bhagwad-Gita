package com.example.savik.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShlokaService {

    fun getRetrofit():Retrofit{
        val response = Retrofit.Builder()
            .baseUrl("https://bhagavadgitaapi.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return response
    }

    fun getInterface(retrofit: Retrofit):ShlokaInerface{
        return retrofit.create(ShlokaInerface::class.java)
    }

}