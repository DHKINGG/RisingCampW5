package com.example.risingcampw5

import android.app.Application

class MyApplication : Application() {
    companion object {  // 어떤 파일에서도 쓸수있게
        const val baseUrl = "https://kr.api.riotgames.com"
        const val asiaBaseUrl = "https://asia.api.riotgames.com"
        const val apiKey = "RGAPI-628a89a2-48b9-4485-a705-88ff839da75c" //09월 05일 만료 , 만료시 다시 받아와야함 Api_key
    }
}