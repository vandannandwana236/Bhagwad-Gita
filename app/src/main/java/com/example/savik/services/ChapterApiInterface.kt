package com.example.savik.services
import com.example.savik.models.chepters.CheptersItem
import retrofit2.Call
import retrofit2.http.GET

interface ChapterApiInterface {
    @GET("/chapters/")
    fun getChapters():Call<List<CheptersItem>>

}