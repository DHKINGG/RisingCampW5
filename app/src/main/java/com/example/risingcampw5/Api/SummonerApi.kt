package com.example.risingcampw5.Api

import com.example.risingcampw5.Model.Summoner
import com.example.risingcampw5.MyApplication
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//get :  서버에서 데이터를 받아와
//post : 서버에 데이터값 변경 요청 ex) 게시판에 글 업로드시에 서버에 데이터를 전달해서 서버를 변경해달라고 요청

interface SummonerApi {
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    fun getSummoner(
        @Path("summonerName") summonerName: String,
        @Query("api_key") apiKey: String
    ): Call<Summoner>

    companion object {

        fun create(): SummonerApi {
            return Retrofit.Builder()
                .baseUrl(MyApplication.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SummonerApi::class.java)
        }
    }
}