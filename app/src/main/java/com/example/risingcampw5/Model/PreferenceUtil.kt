package com.example.risingcampw5.Model

import android.content.Context
import android.content.SharedPreferences
import com.example.risingcampw5.MyApplication
import com.example.risingcampw5.MyApplication.Companion.prefs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE) //MODE_PRIVATE : 생성한 application에서만 사용 가능

    // gson : object에서 json으로, json에서 object로 바꿔주는 도구
    // json : 데이터가 string화 된 것

    fun getSummonerList(key: String): MutableList<Summoner> {
        val json = prefs.getString(key, "").toString()    // sp에서 꺼내옴
        val gson = Gson()
        val token: TypeToken<MutableList<Summoner>> = object : TypeToken<MutableList<Summoner>>(){} // 어떤타입의 토큰을 가져올건지
        val summonerList: MutableList<Summoner>? = gson.fromJson(json, token.type)   // gson을 사용해서 json을 object로 바꿈

        if (summonerList == null) {
            return mutableListOf()
        } else {
            return summonerList
        }
    }

    fun setSummonerList(key: String, listObject: MutableList<Summoner>) {
        val gson = Gson()
        val json: String = gson.toJson(listObject)    // gson을 사용해서 object를 json으로 바꿈
        prefs.edit().putString(key, json).apply()   // sp에 저장
    }

    fun clearSummonerList() {
        prefs.edit().remove(MyApplication.summonerListPrefKey).commit()
    }
}