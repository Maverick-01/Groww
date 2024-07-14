package com.maverick.groww.owner.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.maverick.groww.R
import com.maverick.groww.databinding.ActivityMainBinding
import com.maverick.groww.owner.adapter.ViewPagerAdapter
import com.maverick.groww.owner.fragment.TopGainerFragment
import com.maverick.groww.owner.fragment.TopLoserFragment
import com.maverick.groww.owner.observer.TopGainerLoserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TopGainerLoserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        val fragmentList = arrayOf(TopGainerFragment(), TopLoserFragment())
        binding.viewpager.adapter =
            ViewPagerAdapter(fragmentActivity = this, fragmentList = fragmentList)
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> "Top Gainer"
                1 -> "Top Loser"
                else -> "Top Gainer"
            }
        }.attach()
    }
}