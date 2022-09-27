package com.dev.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dev.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mAdView: AdView
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
        with(rvReminderList) {
            rVAdapter = ReminderItemListAdapter()
            adapter = rVAdapter
            recycledViewPool.setMaxRecycledViews(
                ReminderItemListAdapter.VIEW_TYPE_ENABLED,
                ReminderItemListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ReminderItemListAdapter.VIEW_TYPE_DISABLED,
                ReminderItemListAdapter.MAX_POOL_SIZE
            )
        }
        rVAdapter.onReminderItemClickListener = {
            viewModel.changeEnableState(it)
        }
    }


}