package com.example.risingcampw5.Model

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("participants") var participants: ArrayList<Participants>,
    @SerializedName("gameDuration") var gameDuration : Long,
    @SerializedName("gameCreation") var gameCreation : Long
)