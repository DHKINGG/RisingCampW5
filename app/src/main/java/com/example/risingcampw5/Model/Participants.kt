package com.example.risingcampw5.Model

import com.google.gson.annotations.SerializedName

data class Participants(

    @SerializedName("win") var win: Boolean,
    @SerializedName("kills") var kills: Int,
    @SerializedName("deaths") var deaths: Int,
    @SerializedName("assists") var assists: Int,
    @SerializedName("challenges") var challenges: Challenges,
    @SerializedName("doubleKills") var doubleKills: Int,
    @SerializedName("tripleKills") var tripleKills: Int,
    @SerializedName("quadraKills") var quadraKills: Int,
    @SerializedName("pentaKills") var pentaKills: Int,
    @SerializedName("summonerId") var summonerId: String
)

