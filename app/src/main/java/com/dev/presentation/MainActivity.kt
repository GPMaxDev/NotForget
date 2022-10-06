package com.dev.presentation

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dev.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


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
            rVAdapter.submitList(it)

        }

        initialiseAdvertisingBanner()
    }

    private fun initialiseAdvertisingBanner() {
        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
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
        setupLongClickListener()

        setupShortClickListener()

        setupSwipeListener(rvReminderList)


    }

    private fun setupSwipeListener(rvReminderList: RecyclerView) {
        val callBack = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            // experim
            private val deleteColor = ContextCompat.getColor(baseContext, R.color.deleteCellColor)
            private val deleteIcon = R.drawable.ic_delete_cell

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = rVAdapter.currentList[viewHolder.bindingAdapterPosition]
                viewModel.deleteReminderItem(item)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(c,recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftActionIcon(deleteIcon)
                    .addSwipeLeftBackgroundColor(deleteColor)
                    .create()
                    .decorate()
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(rvReminderList)
    }

    private fun setupShortClickListener() {
        rVAdapter.onReminderItemShortClickListener = {
            Log.d("Edit view", "remind item id ${it.id}")
        }
    }

    private fun setupLongClickListener() {
        rVAdapter.onReminderItemClickListener = {
            viewModel.changeEnableState(it)
        }
    }


}