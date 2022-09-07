package com.example.risingcampw5.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.risingcampw5.Model.Summoner
import com.example.risingcampw5.databinding.*

class SearchMultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var searchData = mutableListOf<Summoner>()
    lateinit var adapterContext: Context


    inner class SearchHeaderHolder(val binding: ItemViewSearchHeaderMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
        }
    }


    inner class SearchMultiHolder(val binding: ItemViewSearchMultiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableList<Summoner>) {
            binding.rvSearch.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.VERTICAL, false)

            val recyclerAdapter = SearchAdapter()

            recyclerAdapter.list = item
            recyclerAdapter.setContext(adapterContext)
            binding.rvSearch.adapter = recyclerAdapter
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                return SearchHeaderHolder(
                    ItemViewSearchHeaderMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return SearchMultiHolder(
                    ItemViewSearchMultiBinding.inflate(
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
            0 -> (holder as SearchMultiAdapter.SearchHeaderHolder).bind()
            else -> (holder as SearchMultiAdapter.SearchMultiHolder).bind(searchData)
        }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return 2
    }

    fun setContext(context: Context) {
        adapterContext = context
    }
}