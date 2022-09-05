package com.example.risingcampw5.Api

import com.example.risingcampw5.Model.Summoner
import com.example.risingcampw5.MyApplication
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MatchByPuuidApi {
    @GET("/lol/match/v5/matches/by-puuid/{puuid}/ids")
    fun getMatchByPuuid(
        @Path("puuid") puuid: String,
        @Query("api_key") apiKey: String,
        @Query("count") count: Int
    ): Call<ArrayList<String>>

    companion object {

        fun create(): MatchByPuuidApi {
            return Retrofit.Builder()
                .baseUrl(MyApplication.asiaBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MatchByPuuidApi::class.java)
        }
    }
}