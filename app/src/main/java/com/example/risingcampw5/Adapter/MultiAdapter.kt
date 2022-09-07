package com.example.risingcampw5.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingcampw5.Model.Match
import com.example.risingcampw5.Model.SeasonTier
import com.example.risingcampw5.Model.Summoner
import com.example.risingcampw5.MyApplication
import com.example.risingcampw5.R
import com.example.risingcampw5.databinding.ItemViewMatchMultiBinding
import com.example.risingcampw5.databinding.ItemViewSeasonTierMultiBinding
import com.example.risingcampw5.databinding.ItemViewTopInfoBinding

class MultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var matchDataList = mutableListOf<Match>()
    var topInfoData = Summoner("", 0, 0, "", "", "", 0)
    lateinit var adapterContext: Context
    var summonerId: String = ""
    var seasonTierList = mutableListOf<SeasonTier>()

    inner class TopInfoHolder(val binding: ItemViewTopInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Summoner) {
            binding.tvLevel.text = item.summonerLevel.toString()
            binding.tvSummonerNickname.text = item.name
            Glide.with(adapterContext)
                .load("${MyApplication.profileIconUrl}${item.profileIconId}.png")
                .into(binding.ivSummonerProfile)
            Glide.with(adapterContext)
                .load("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg")
                .into(binding.ivBackground)
        }
    }

    inner class MatchHistoryHolder(val binding: ItemViewMatchMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableList<Match>) {
            binding.rvGameResultMain.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.VERTICAL, false)
            val recyclerAdapter = MatchAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.summonerId = summonerId
            recyclerAdapter.setContext(adapterContext)
            binding.rvGameResultMain.adapter = recyclerAdapter
        }
    }


    inner class SeasonTierHolder(val binding: ItemViewSeasonTierMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableList<SeasonTier>) {
            binding.rvSeasonMain.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.HORIZONTAL, false)
            val recyclerAdapter = SeasonTierAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.setContext(adapterContext)
            binding.rvSeasonMain.adapter = recyclerAdapter
        }
    }

    // 1. game_result_multi item뷰를 띄울 ViewHolder생성
    // 2. GameResultMulti 뷰홀더 내에 bind함수 만들어서 itemView안의 리사이클러뷰에 어댑터연결 여기서 데이터리스트는 없으니까 일단 주석처리
    // 3. getItemViewType 오버라이드 함수 생성

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                return TopInfoHolder(
                    ItemViewTopInfoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            1 -> {
                return SeasonTierHolder(
                    ItemViewSeasonTierMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return MatchHistoryHolder(
                    ItemViewMatchMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> (holder as TopInfoHolder).bind(topInfoData)
            1 -> (holder as SeasonTierHolder).bind(seasonTierList)
            else -> (holder as MatchHistoryHolder).bind(matchDataList)
        }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return 3
    }

    fun setContext(context: Context) {
        adapterContext = context
    }
}