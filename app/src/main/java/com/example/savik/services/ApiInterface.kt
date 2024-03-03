package com.example.savik.services

import com.example.savik.models.Post
import retrofit2.Call
import retrofit2.http.GET
//https://jsonplaceholder.typicode.com/posts
interface ApiInterface {
    @GET("/posts")
    fun getPost():Call<List<Post>>
}