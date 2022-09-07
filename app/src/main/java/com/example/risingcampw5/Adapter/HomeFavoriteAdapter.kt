package com.example.risingcampw5.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingcampw5.Model.HomeFavorite
import com.example.risingcampw5.Model.Match
import com.example.risingcampw5.Model.Participants
import com.example.risingcampw5.MyApplication
import com.example.risingcampw5.R
import com.example.risingcampw5.databinding.ItemViewFavoriteSummonerBinding
import com.example.risingcampw5.databinding.ItemViewMatchBinding
import kotlin.math.roundToInt

class HomeFavoriteAdapter :
    RecyclerView.Adapter<HomeFavoriteAdapter.Holder>() {
    var list = mutableListOf<HomeFavorite>()
    lateinit var adapterContext: Context

    inner class Holder(val binding: ItemViewFavoriteSummonerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeFavorite) {
            Glide.with(adapterContext)
                .load(item.image)
                .into(binding.ivFavoriteSummonerProfile)

            binding.tvFavoriteNickname.text = item.nickName
            binding.tvFavoriteLevel.text = item.level
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFavoriteAdapter.Holder {
        return Holder(
            ItemViewFavoriteSummonerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setContext(context: Context) {
        adapterContext = context
    }
}