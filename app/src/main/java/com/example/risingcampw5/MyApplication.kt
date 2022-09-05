package com.example.risingcampw5

import android.app.Application

class MyApplication : Application() {
    companion object {  // 어떤 파일에서도 쓸수있게
        const val baseUrl = "https://kr.api.riotgames.com"
        const val asiaBaseUrl = "https://asia.api.riotgames.com"
        const val apiKey = "RGAPI-6517d677-6ed4-4af7-ab25-8afa00f185a2" //09월 05일 만료 , 만료시 다시 받아와야함 Api_key
    }
}