package com.example.risingcampw5.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingcampw5.Adapter.MultiAdapter
import com.example.risingcampw5.Adapter.SearchAdapter
import com.example.risingcampw5.MyApplication
import com.example.risingcampw5.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val adapter = SearchAdapter()

        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvSearchMain.layoutManager = LinearLayoutManager(this) // this : 현재 액티비티의 context를 가져옴
        adapter.setContext(this)
        var summonerList = MyApplication.prefs.getSummonerList(MyApplication.summonerListPrefKey)
        if (summonerList == null) {
            summonerList = mutableListOf()
        }
        adapter.list = summonerList
        binding.rvSearchMain.adapter = adapter

        binding.ivSideArrow.setOnClickListener {
            finish()
        }


        binding.edtSearchChamp.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {

                val nextIntent = Intent(this, MainActivity::class.java)
                nextIntent.putExtra("champ_id", binding.edtSearchChamp.text.toString())
                startActivity(nextIntent)
            }

            true
        }

    }

    override fun onStart() {
        super.onStart()

        var summonerList = MyApplication.prefs.getSummonerList(MyApplication.summonerListPrefKey)
        if (summonerList == null) {
            summonerList = mutableListOf()
        }


    }
}