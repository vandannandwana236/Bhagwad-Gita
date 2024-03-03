package com.example.savik.activities
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.savik.R
import com.example.savik.models.Shloka
import com.example.savik.services.ShlokaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShlokaActivity : AppCompatActivity() {
    private lateinit var schnumber:TextView
    private lateinit var schname:TextView
    private lateinit var snumber:TextView
    private lateinit var shloka:TextView
    private lateinit var nextBtn:Button
    private lateinit var prevBtn:Button
    private lateinit var progressBar: ProgressBar
    private var shnumber = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shloka)

        val chnumber = intent.getIntExtra("chnumber",1)
        val chname = intent.getStringExtra("chname")
        snumber = findViewById(R.id.snumber)
        shloka = findViewById(R.id.shloka)
        nextBtn = findViewById(R.id.nextBtn)
        prevBtn = findViewById(R.id.prevBtn)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        schname = findViewById(R.id.schname)
        schnumber = findViewById(R.id.schnumber)
        schname.text = chname
        schnumber.text = chnumber.toString()

        runOnUiThread {

            getShloka(chnumber,shnumber)

        }

        nextBtn.setOnClickListener {

            runOnUiThread {
                    shnumber +=1
                    getShloka(chnumber,shnumber)
            }

        }

        prevBtn.setOnClickListener {
            if(shnumber>1) {
                runOnUiThread {
                    try {
                        getShloka(chnumber, shnumber)
                        shnumber -= 1
                    } catch (e: Exception) {
                        getShloka(chnumber, shnumber)
                    }
                }
            }
        }




    }

    private fun getShloka(chapter:Int,verse:Int){
        progressBar.visibility = View.VISIBLE
        val service = ShlokaService()
        val retrofit = service.getRetrofit()
        val apiService = service.getInterface(retrofit)
        shloka.text=""
        val call = apiService.getShloka(chapter, verse)
        call.enqueue(object : Callback<Shloka> {
            override fun onResponse(call: Call<Shloka>, response: Response<Shloka>) {

                val slokaResponse = response.body()
                if(slokaResponse != null) {
                    val sloka = slokaResponse.slok
                    shloka.text = sloka
                    snumber.text = shnumber.toString()
                    progressBar.visibility = View.GONE
                }else{
                    shloka.text = "No more Shlokas in this chapter"
                    progressBar.visibility = View.GONE
                    shnumber -=1
                }
            }

            override fun onFailure(call: Call<Shloka>, t: Throwable) {
                Log.e("API Call", "Failed to fetch sloka: ${t.message}", t)
            }
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}