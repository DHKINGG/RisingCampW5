package com.example.risingcampw5.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingcampw5.Model.Match
import com.example.risingcampw5.Model.Summoner
import com.example.risingcampw5.MyApplication
import com.example.risingcampw5.databinding.ItemViewMatchBinding
import com.example.risingcampw5.databinding.ItemViewSearchBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.Holder>() {
    lateinit var adapterContext: Context
    var list = mutableListOf<Summoner>()

    inner class Holder(val binding: ItemViewSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Summoner, position: Int) {
            Glide.with(adapterContext)
                .load("${MyApplication.profileIconUrl}${item.profileIconId}.png")
                .into(binding.ivSearchSummonerIcon)

            binding.tvSearchSummonerId.text = item.name
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.Holder {
        return Holder(
            ItemViewSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: SearchAdapter.Holder,
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