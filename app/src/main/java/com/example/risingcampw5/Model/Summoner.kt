package com.example.risingcampw5.Model

import com.google.gson.annotations.SerializedName

data class Summoner(
    @SerializedName("accountId") var accountId: String,  // @SerializedName("puuid") 해당 괄호안에 따옴표안에있는 값을 가져온다로 생각
    @SerializedName("profileIconId") var profileIconId: Int,
    @SerializedName("revisionDate") var revisionDate: Long,
    @SerializedName("name") var name: String,
    @SerializedName("id") var id: String,
    @SerializedName("puuid") var puuid: String,
    @SerializedName("summonerLevel") var summonerLevel: Long,
    var isLike : Boolean
)