package com.example.risingcampw5.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.risingcampw5.Model.Match
import com.example.risingcampw5.databinding.ItemViewMatchMultiBinding

class MultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var matchDataList = mutableListOf<Match>()
    lateinit var adapterContext: Context
    var summonerId: String = ""

    inner class MatchHistoryHolder(val binding: ItemViewMatchMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableList<Match>) {
            binding.rvGameResultMain.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.VERTICAL, false)
            val recyclerAdapter = MatchAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.summonerId = summonerId
            binding.rvGameResultMain.adapter = recyclerAdapter
        }
    }

    // 1. game_result_multi item뷰를 띄울 ViewHolder생성
    // 2. GameResultMulti 뷰홀더 내에 bind함수 만들어서 itemView안의 리사이클러뷰에 어댑터연결 여기서 데이터리스트는 없으니까 일단 주석처리
    // 3. getItemViewType 오버라이드 함수 생성


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        if (viewType == 0) {
//            return MatchHistoryHolder(
//                GameResultMultiBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//            )
//        }
        return MatchHistoryHolder(
            ItemViewMatchMultiBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            (holder as MatchHistoryHolder).bind(matchDataList)
        }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun setContext(context: Context) {
        adapterContext = context
    }

    fun setMatchData(matches: ArrayList<Match>) {
        for (match in matches) {
            matchDataList.add(match)
        }
    }
}