package com.dev.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dev.R
import com.dev.domain.ReminderItem
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    lateinit var mAdView: AdView
    private lateinit var rVAdapter: ReminderItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.reminderList.observe(this) {
            Log.d("mainActivityTest", it.toString()) // potom ubrat!!!!
            rVAdapter.reminderList = it

        }
        //reklama baner
        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest) // podumat kak cdelat krasivo
    }

    private fun setupRecyclerView() {
        val rvReminderList = findViewById<RecyclerView>(R.id.recyclerView)
        rVAdapter = ReminderItemListAdapter()
        rvReminderList.adapter = rVAdapter

    }


}