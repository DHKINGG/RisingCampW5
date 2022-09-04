package com.example.risingcampw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.risingcampw5.Api.SummonerApi
import com.example.risingcampw5.Model.Summoner
import com.example.risingcampw5.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        val api = SummonerApi.create()
        api.getSummoner("동휘가춤을춰요", MyApplication.apiKey).enqueue(object : Callback<Summoner> {
            override fun onResponse(call: Call<Summoner>, response: Response<Summoner>) { // 데이터 받아오는게 성공시 이 함수 실행
                val responseSummoner = response.body()

                if (responseSummoner != null) {
                    binding.tvMain.text = "소환사이름=${responseSummoner.name}, 소환사레벨은${responseSummoner.summonerLevel}"
                }
            }

            override fun onFailure(call: Call<Summoner>, t: Throwable) { // 데이터 받아오는게 실패시 이 함수 실행
                TODO("Not yet implemented")
            }

        })
    }
}
