package com.example.risingcampw5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import com.example.risingcampw5.databinding.ActivityMainBinding
import com.example.risingcampw5.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.edtSearchChamp.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {

                val nextIntent = Intent(this, MainActivity::class.java)
                nextIntent.putExtra("champ_id", binding.edtSearchChamp.text.toString())
                startActivity(nextIntent)
            }

            true
        }

    }
}