package com.example.risingcampw5.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingcampw5.Activity.MainActivity
import com.example.risingcampw5.Activity.SearchActivity
import com.example.risingcampw5.Model.HomeFavorite
import com.example.risingcampw5.Model.HomePatchNote
import com.example.risingcampw5.Model.Match
import com.example.risingcampw5.Model.Summoner
import com.example.risingcampw5.MyApplication
import com.example.risingcampw5.databinding.*

class HomeMultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var adapterContext: Context
    var favoriteList = mutableListOf<HomeFavorite>()
    var patchNoteList = mutableListOf<HomePatchNote>()

    inner class SearchHolder(val binding: ItemViewHomeSearchMultiBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            Glide.with(adapterContext)
                .load("http://ddragon.leagueoflegends.com/cdn/12.16.1/img/profileicon/588.png")
                .into(binding.ivWhatMyKda)

            binding.clHomeSearch.setOnClickListener {
                val intent = Intent(adapterContext, SearchActivity::class.java)
                adapterContext.startActivity(intent)
            }
        }
    }

    inner class FavoriteSummonerHolder(val binding: ItemViewFavoriteSummonerMultiBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableList<HomeFavorite>) {
            binding.rvFavorite.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.HORIZONTAL, false)
            val recyclerAdapter = HomeFavoriteAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.setContext(adapterContext)
            binding.rvFavorite.adapter = recyclerAdapter
        }
    }

    inner class PatchNoteHolder(val binding: ItemViewPatchNoteMultiBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableList<HomePatchNote>) {
            binding.rvPatchNote.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.HORIZONTAL, false)
            val recyclerAdapter = HomePatchNoteAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.setContext(adapterContext)
            binding.rvPatchNote.adapter = recyclerAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                return SearchHolder(
                    ItemViewHomeSearchMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            1 -> {
                return FavoriteSummonerHolder(
                    ItemViewFavoriteSummonerMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return PatchNoteHolder(
                    ItemViewPatchNoteMultiBinding.inflate(
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
            0 -> (holder as SearchHolder).bind()
            1 -> (holder as FavoriteSummonerHolder).bind(favoriteList)
            else -> (holder as PatchNoteHolder).bind(patchNoteList)
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