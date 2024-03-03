package com.example.savik.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChapterService {

    fun getRetrfit():Retrofit{
        val response = Retrofit.Builder()
            .baseUrl("https://bhagavadgitaapi.in")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return response
    }

    fun getInterface(retrofit: Retrofit):ChapterApiInterface{
        return retrofit.create(ChapterApiInterface::class.java)
    }

}