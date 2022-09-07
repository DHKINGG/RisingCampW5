package com.example.risingcampw5.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingcampw5.Model.HomeFavorite
import com.example.risingcampw5.Model.HomePatchNote
import com.example.risingcampw5.databinding.ItemViewFavoriteSummonerBinding
import com.example.risingcampw5.databinding.ItemViewPatchNoteBinding

class HomePatchNoteAdapter :
    RecyclerView.Adapter<HomePatchNoteAdapter.Holder>() {
    var list = mutableListOf<HomePatchNote>()
    lateinit var adapterContext: Context

    inner class Holder(val binding: ItemViewPatchNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomePatchNote) {
            Glide.with(adapterContext)
                .load(item.image)
                .into(binding.ivPatchNote)

            binding.tvPatchNote.text = item.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePatchNoteAdapter.Holder {
        return Holder(
            ItemViewPatchNoteBinding.inflate(
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