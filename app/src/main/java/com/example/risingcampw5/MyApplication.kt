package com.example.risingcampw5

import android.app.Application

class MyApplication : Application() {
    companion object {  // 어떤 파일에서도 쓸수있게
        const val baseUrl = "https://kr.api.riotgames.com"
        const val apiKey = "RGAPI-f62558ac-0a7d-42ed-a41e-e4ec355b6049" //09월 05일 만료 , 만료시 다시 받아와야함 Api_key

    }
}