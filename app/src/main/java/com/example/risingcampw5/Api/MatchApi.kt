package com.example.risingcampw5.Api

import com.example.risingcampw5.Model.Match
import com.example.risingcampw5.MyApplication
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MatchApi {
    @GET("/lol/match/v5/matches/{matchId}")
    fun getMatch(
        @Path("matchId") matchId: String,
        @Query("api_key") apiKey: String
    ): Call<Match>

    companion object {

        fun create(): MatchApi {
            return Retrofit.Builder()
                .baseUrl(MyApplication.asiaBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MatchApi::class.java)
        }
    }
}