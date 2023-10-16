package com.example.musicplayer

import MyData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView=findViewById(R.id.recyclerView)
        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData=retrofitBuilder.getData("eminem")

        //ctrl+chift+space dbakr aya h pura objectr vala
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                //if the api call is success then this call is executed
                val dataList=response.body()?.data!!
                //val textView=findViewById<TextView>(R.id.HelloText)
                    //textView.text=dataList.toString()

                //sets link btw adapter and recyclerview
                myAdapter= MyAdapter(this@MainActivity,dataList)
                myRecyclerView.adapter=myAdapter
                myRecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)

                Log.d("TAG:onResponse","onResponse:"+response.body())
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                //if the api call is failure then this call is executed
                Log.d("TAG:OnFailure","onFailure:"+t.message)
            }
        })
    }
}