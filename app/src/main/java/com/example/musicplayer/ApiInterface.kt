package com.example.musicplayer

import MyData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {


    @Headers("X-RapidAPI-Key:7b57a7db91mshe66462096f0f457p177ac0jsn0c12b9e06ab9"
    ,"X-RapidAPI-Host:deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q")query: String):Call<MyData>
}