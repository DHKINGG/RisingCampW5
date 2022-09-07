package com.example.risingcampw5.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingcampw5.Adapter.HomeMultiAdapter
import com.example.risingcampw5.Model.HomeFavorite
import com.example.risingcampw5.Model.HomePatchNote
import com.example.risingcampw5.R
import com.example.risingcampw5.databinding.ActivityHomeBinding
import com.example.risingcampw5.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var favoriteList = mutableListOf<HomeFavorite>()
    private var patchNoteList = mutableListOf<HomePatchNote>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setFavoriteList()
        setPatchNoteList()

        val adapter = HomeMultiAdapter()
        binding.rvHomeMulti.layoutManager = LinearLayoutManager(this) // this : 현재 액티비티의 context를 가져옴
        adapter.setContext(this)
        adapter.favoriteList = favoriteList
        adapter.patchNoteList = patchNoteList
        binding.rvHomeMulti.adapter = adapter
    }

    private fun setFavoriteList() {
        favoriteList.add(HomeFavorite("http://ddragon.leagueoflegends.com/cdn/12.16.1/img/profileicon/685.png", "adkasld", "173"))
        favoriteList.add(HomeFavorite("http://ddragon.leagueoflegends.com/cdn/12.16.1/img/profileicon/686.png", "동휘가춤을춰요", "200"))
        favoriteList.add(HomeFavorite("http://ddragon.leagueoflegends.com/cdn/12.16.1/img/profileicon/687.png", "망글룰루", "17"))
        favoriteList.add(HomeFavorite("http://ddragon.leagueoflegends.com/cdn/12.16.1/img/profileicon/688.png", "우르우르곳", "34"))
        favoriteList.add(HomeFavorite("http://ddragon.leagueoflegends.com/cdn/12.16.1/img/profileicon/689.png", "히히유튜브", "121"))
        favoriteList.add(HomeFavorite("http://ddragon.leagueoflegends.com/cdn/12.16.1/img/profileicon/684.png", "헤헤유튜브", "125"))
        favoriteList.add(HomeFavorite("http://ddragon.leagueoflegends.com/cdn/12.16.1/img/profileicon/683.png", "말왕", "253"))
        favoriteList.add(HomeFavorite("http://ddragon.leagueoflegends.com/cdn/12.16.1/img/profileicon/682.png", "괴물쥐", "332"))
        favoriteList.add(HomeFavorite("http://ddragon.leagueoflegends.com/cdn/12.16.1/img/profileicon/681.png", "돈절래", "13"))
        favoriteList.add(HomeFavorite("http://ddragon.leagueoflegends.com/cdn/12.16.1/img/profileicon/680.png", "캬하하", "113"))
    }

    private fun setPatchNoteList() {
        patchNoteList.add(HomePatchNote("https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/bltdf6f30def332c3df/6298387236712c4150de038a/060722_Patch_12_11_Notes_Banner.jpg", "Patch 12.16 Notes"))
        patchNoteList.add(HomePatchNote("https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt77b3c2f2473270b6/5e4d89e5534f9854b20af586/LOL_PROMOART_10_banner.jpg", "Patch 12.15 Notes"))
        patchNoteList.add(HomePatchNote("https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/bltc7f43fe2bdd3f1e1/62d8966ed9851110d46cf998/072622_LoL_Patch_12_14_Notes_Banner_v2.jpg", "Patch 12.14 notes"))
        patchNoteList.add(HomePatchNote("https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt99297fcea9a48f20/62cdcda965187e36e4763f05/Star-Guardian-Key-Visual-1.jpg", "Patch 12.13 notes"))
        patchNoteList.add(HomePatchNote("https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt30d3d484527d8e5a/5fb5eee4fd99385ff6006de3/LOL_PROMOART_9-1.jpg", "Patch 12.12 notes"))
    }
}