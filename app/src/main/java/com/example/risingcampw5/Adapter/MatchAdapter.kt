package com.example.risingcampw5.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingcampw5.Model.Match
import com.example.risingcampw5.Model.Participants
import com.example.risingcampw5.databinding.ItemViewMatchBinding
import kotlin.math.roundToInt

class MatchAdapter :
    RecyclerView.Adapter<MatchAdapter.Holder>() {
    // 1. 모델 리스트 생성(타입은 MutableList<Model>)
    var list = mutableListOf<Match>()
    var summonerId: String = ""

    inner class Holder(val binding: ItemViewMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // 2. 뷰홀더 안에 bind함수 구현
        fun bind(item: Match, position: Int) {
            for (participant in item.info.participants) {
                if (summonerId == participant.summonerId) {
                    binding.tvMatchHistoryIsWin.text = if (participant.win) "승" else "패"
                    binding.tvSummonerKda.text =
                        "${participant.kills} / ${participant.deaths} / ${participant.assists}"
                    binding.tvKillParticipation.text =
                        calculateKillParticipation(participant.challenges.killParticipation)
                    if (getMultiKillText(participant) == "") {
                        binding.tvMultiKill.visibility = View.GONE
                    } else {
                        binding.tvMultiKill.visibility = View.VISIBLE
                        binding.tvMultiKill.text = getMultiKillText(participant)
                    }
                }
            }
            binding.tvFightTime.text = calculateDuration(item.info.gameDuration)
        }

        private fun calculateDuration(gameDuration: Long): String {
            val minute = gameDuration / 60
            val sec = gameDuration % 60

            var returnValue = ""
            if (minute < 10) {
                returnValue += "0"
            }
            returnValue += minute.toString()
            returnValue += ":"

            if (sec < 10) {
                returnValue += "0"
            }
            returnValue += sec.toString()
            return returnValue
        }

        private fun calculateKillParticipation(killParticipants: Float): String {
            var returnValue = (killParticipants * 100).roundToInt()
            return "킬관여 ${returnValue.toString()}%"
        }

        private fun getMultiKillText(participant: Participants): String {
            return if (participant.pentaKills > 0) "펜타킬"
            else if (participant.quadraKills > 0) "쿼드라킬"
            else if (participant.tripleKills > 0) "트리플킬"
            else if (participant.doubleKills > 0) "더블킬"
            else ""
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MatchAdapter.Holder {
        return Holder(
            ItemViewMatchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MatchAdapter.Holder,
        position: Int
    ) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int {
        // 4. model리스트의 사이즈만큼 리턴
        return list.size
    }
}