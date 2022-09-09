package com.example.risingcampw5.Activity

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingcampw5.Adapter.MultiAdapter
import com.example.risingcampw5.Api.MatchApi
import com.example.risingcampw5.Api.MatchByPuuidApi
import com.example.risingcampw5.Api.SummonerApi
import com.example.risingcampw5.Model.Match
import com.example.risingcampw5.Model.SeasonTier
import com.example.risingcampw5.Model.Summoner
import com.example.risingcampw5.MyApplication
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
    private var matchIds = arrayListOf<String>()
    private val adapter = MultiAdapter()
    private lateinit var summoner: Summoner
    private var apiCall = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvMain.layoutManager = LinearLayoutManager(this) // this : 현재 액티비티의 context를 가져옴
        adapter.setContext(this)
        binding.rvMain.adapter = adapter

        setSeasonTier()
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
                    summoner = responseSummoner
                    summonerName = responseSummoner.name
                    summonerId = responseSummoner.id
                    summonerLevel = responseSummoner.summonerLevel

                    var summonerList = MyApplication.prefs.getSummonerList(MyApplication.summonerListPrefKey)
                    if (summonerList == null) {
                        summonerList = mutableListOf()
                    }
                    summonerList.add(summoner)
                    MyApplication.prefs.setSummonerList(MyApplication.summonerListPrefKey, summonerList)


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
                    matchIds.addAll(responseMatches)
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

                apiCall++
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
        if (apiCall == matchIds.size) {
            matches.sortByDescending { it.info.gameCreation }
            adapter.setContext(this)
            adapter.matchDataList = matches
            adapter.summonerId = summonerId
            adapter.topInfoData = summoner
            adapter.notifyDataSetChanged()
        }
    }

    private fun setSeasonTier() {
        var seasonTier = mutableListOf<SeasonTier>()
        seasonTier.add(SeasonTier("S2021", "GOLD 3"))
        seasonTier.add(SeasonTier("S2020", "GOLD 4"))
        seasonTier.add(SeasonTier("S9", "PLATINUM 4"))
        seasonTier.add(SeasonTier("S8", "GOLD 5"))
        seasonTier.add(SeasonTier("S7", "GOLD 3"))
        seasonTier.add(SeasonTier("S6", "GOLD 4"))
        seasonTier.add(SeasonTier("S5", "GOLD 5"))
        seasonTier.add(SeasonTier("S4", "SILVER 2"))

        adapter.seasonTierList = seasonTier
        adapter.notifyDataSetChanged()
    }
}
