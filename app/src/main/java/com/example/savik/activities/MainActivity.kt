package com.example.savik.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savik.R
import com.example.savik.adapters.ChaptersAdapter
import com.example.savik.models.chepters.CheptersItem
import com.example.savik.services.ChapterService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var chapterList:List<CheptersItem>
    private lateinit var ChaptersRv:RecyclerView
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ChaptersRv = findViewById(R.id.chaptersRv)


        val service = ChapterService()
        val retrofit = service.getRetrfit()
        val apiInterface = service.getInterface(retrofit)
        val call:Call<List<CheptersItem>> = apiInterface.getChapters()

        call.enqueue(object :Callback<List<CheptersItem>>{
            override fun onResponse(call: Call<List<CheptersItem>>, response: Response<List<CheptersItem>>) {
                val posts = response.body()
                chapterList = posts?.let { ArrayList(it) }!!
                runOnUiThread {
                    getData(chapterList)
                }

            }

            override fun onFailure(call: Call<List<CheptersItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Data Finding Failed",Toast.LENGTH_LONG).show()
            }

        })


    }

    fun getData(list:List<CheptersItem>){
        ChaptersRv.layoutManager = LinearLayoutManager(this)
        ChaptersRv.adapter = ChaptersAdapter(list)
    }

}