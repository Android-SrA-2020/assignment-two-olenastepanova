package com.nbcc.rickmortyquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val binding = DataBindingUtil
//            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
}
