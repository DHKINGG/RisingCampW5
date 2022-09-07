package com.example.risingcampw5.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingcampw5.Model.SeasonTier
import com.example.risingcampw5.databinding.ItemViewSeasonTierBinding

class SeasonTierAdapter : RecyclerView.Adapter<SeasonTierAdapter.Holder>(){
    var list = mutableListOf<SeasonTier>()
    lateinit var adapterContext: Context

    inner class Holder(val binding : ItemViewSeasonTierBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : SeasonTier, position: Int) {
            binding.tvSeasonTier1.text = "${item.year} ${item.tier}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeasonTierAdapter.Holder {
        return Holder(
            ItemViewSeasonTierBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: SeasonTierAdapter.Holder,
        position: Int
    ) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int {
        // 4. model리스트의 사이즈만큼 리턴
        return list.size
    }

    fun setContext(context: Context) {
        adapterContext = context
    }
}