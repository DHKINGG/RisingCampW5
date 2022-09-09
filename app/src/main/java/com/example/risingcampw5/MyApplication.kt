package com.example.risingcampw5

import android.app.Application
import com.example.risingcampw5.Model.PreferenceUtil

class MyApplication : Application() {
    companion object {  // 어떤 파일에서도 쓸수있게
        lateinit var prefs: PreferenceUtil
        const val summonerListPrefKey = "summoner_list"
        const val baseUrl = "https://kr.api.riotgames.com"
        const val asiaBaseUrl = "https://asia.api.riotgames.com"
        const val apiKey = "RGAPI-79528990-139e-4cd7-afe2-48ffd777f88b" //  만료시 다시 받아와야함 Api_key
        const val profileIconUrl = "http://ddragon.leagueoflegends.com/cdn/12.16.1/img/profileicon/"
        const val championUrl = "http://ddragon.leagueoflegends.com/cdn/12.16.1/img/champion/"
        const val itemUrl = "http://ddragon.leagueoflegends.com/cdn/12.16.1/img/item/"
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}