package com.example.risingcampw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.risingcampw5.Api.MatchApi
import com.example.risingcampw5.Api.MatchByPuuidApi
import com.example.risingcampw5.Api.SummonerApi
import com.example.risingcampw5.Model.Match
import com.example.risingcampw5.Model.Summoner
import com.example.risingcampw5.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var summonerName: String
    private lateinit var summonerId: String
    private var summonerLevel: Long = 0
    private var matches = arrayListOf<Match>()
    private lateinit var matchIds: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()

        var summonerName = ""
        if (intent.hasExtra("champ_id")) {
            summonerName = intent.getStringExtra("champ_id").toString()
        }
        getSummoner(summonerName)
    }

    private fun getSummoner(pSummonerName: String) {
        val api = SummonerApi.create()
        api.getSummoner(pSummonerName, MyApplication.apiKey).enqueue(object : Callback<Summoner> {
            override fun onResponse(call: Call<Summoner>, response: Response<Summoner>) { // 데이터 받아오는게 성공시 이 함수 실행
                val responseSummoner = response.body()

                if (responseSummoner != null) {
                    summonerName = responseSummoner.name
                    summonerId = responseSummoner.id
                    summonerLevel = responseSummoner.summonerLevel

                    val puuid = responseSummoner.puuid
                    getMatchByPuuid(puuid)
                }
            }

            override fun onFailure(call: Call<Summoner>, t: Throwable) { // 데이터 받아오는게 실패시 이 함수 실행
                TODO("Not yet implemented")
            }

        })
    }

    private fun getMatchByPuuid(puuid: String) {
        val api = MatchByPuuidApi.create()
        api.getMatchByPuuid(puuid, MyApplication.apiKey, 20).enqueue(object : Callback<ArrayList<String>> {
            override fun onResponse(call: Call<ArrayList<String>>, response: Response<ArrayList<String>>) { // 데이터 받아오는게 성공시 이 함수 실행
                val responseMatches = response.body()

                if (responseMatches != null) {
                    matchIds = responseMatches
                    for (match in responseMatches) {
                        getMatch(match)
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) { // 데이터 받아오는게 실패시 이 함수 실행
                TODO("Not yet implemented")
            }

        })
    }

    private fun getMatch(matchId: String) {
        val api = MatchApi.create()
        api.getMatch(matchId, MyApplication.apiKey).enqueue(object : Callback<Match> {
            override fun onResponse(call: Call<Match>, response: Response<Match>) { // 데이터 받아오는게 성공시 이 함수 실행
                val responseMatch = response.body()

                if (responseMatch != null) {
                    for (participant in responseMatch.info.participants) {
                        if (participant.summonerId == summonerId) {
                            matches.add(responseMatch)
                            sortMatches()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Match>, t: Throwable) { // 데이터 받아오는게 실패시 이 함수 실행
                TODO("Not yet implemented")
            }

        })
    }

    private fun sortMatches() {
        if (matches.size == matchIds.size) {
            matches.sortByDescending { it.info.gameCreation }

            for (match in matches) {
                for (participant in match.info.participants) {
                    if (participant.summonerId == summonerId) {
                        // 여기서 작업
                    }
                }
            }
        }
    }
}
